package kr.co.mlec.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.mlec.board.service.BoardService;
import kr.co.mlec.board.vo.BoardVO;
import kr.co.mlec.member.vo.MemberVO;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
	public String list(HttpServletRequest request) {
		
		List<BoardVO> boardList = boardService.getBoardList();
		
		request.setAttribute("boardList", boardList);
		
//		return "board/list";
		return "board/list2";
	}
	
	// http://localhost:8080/Mission-Spring/board/detail?no=4
	@GetMapping("/board/detail")
	public String detail(@RequestParam("no") int no, HttpServletRequest request) {
		
		System.out.println("no : " + no);
		
		// no번에 해당 게시글 조회
		BoardVO board = boardService.getBoardByNo(no);
		
		// 공유영역 등록
		request.setAttribute("board", board);
		
		return "board/detail";
	}
	
	// http://localhost:8080/Mission-Spring/board/12
	// http://localhost:8080/Mission-Spring/board/24
	// http://localhost:8080/Mission-Spring/board/3
	@GetMapping("/board/{no}")
	public ModelAndView detail2(@PathVariable("no") int boardNo, HttpServletRequest request) {
		
		System.out.println("boardNo : " + boardNo);
		// no번에 해당 게시글 조회
		BoardVO board = boardService.getBoardByNo(boardNo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/detail");
		mav.addObject("board", board);
		
		// 공유영역 등록
		request.setAttribute("board", board);

		return mav;
	}
	
	@GetMapping("/board/write")
	public void writeForm(Model model, HttpSession session) {
		BoardVO board = new BoardVO();
		
		MemberVO userVO = (MemberVO)session.getAttribute("userVO");
		if(userVO != null) {
			board.setWriter(userVO.getId());
		}
		
		
//		board.setTitle("크하하하"); 입력해놓은 값이 jsp에 남아있게됨 
		
		model.addAttribute("boardVO", board);
	}
	
/*	@PostMapping("/board/write")
	public String write(@RequestParam("title") String title, 
						@RequestParam("writer") String writer,
						@RequestParam("content") String content) {

		System.out.println("title : " + title);
		System.out.println("writer : " + writer);
		System.out.println("content : " + content);
		
		
		return "";
	} 위에 방식으로 쓰는 것을 스프링이 아래의 방법으로 간단하게 만들어줌 title writer content가 boardVO에 있으니 setter방식으로 boardVO에서 그냥 가져옴
	*/
//	public String write(@ModelAttribute ("board") BoardVO board) {  이렇게 쓰면 board로 저장이 되는데 
		
	
	@PostMapping("/board/write")
	public String write(@Valid BoardVO board, BindingResult result) { // 이렇게 사용하면 BoardVO 에 앞자리 소문자로 바뀌어서 boardVO로 저장이 됨
		// BoardVO board 써주면 자동으로 등록이됨 // 스프링의 특징 //
		
		System.out.println(board);
//		if(board.getTitle() == null || board.getTitle().length() == 0 ) {
		if(result.hasErrors()) {
			System.out.println("error발생!!");
			return "board/write";
	} 
		
		boardService.addBoard(board);
		
		return "redirect:/board";

}
	
	
}








