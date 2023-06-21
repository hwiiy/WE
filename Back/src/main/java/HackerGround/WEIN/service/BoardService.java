package HackerGround.WEIN.service;

import HackerGround.WEIN.api.dto.board.BoardModifyRequest;
import HackerGround.WEIN.api.dto.board.BoardRequest;
import HackerGround.WEIN.domain.board.Board;
import HackerGround.WEIN.domain.member.Member;
import HackerGround.WEIN.repository.BoardRepository;
import HackerGround.WEIN.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Board register(Member member, BoardRequest boardRequest) {
        Board board = boardRequest.to_Entity(member);
        return boardRepository.save(board);
    }

    public Board modify(Long id, BoardModifyRequest boardModifyRequest) {
        Board boardById = boardRepository.findBoardById(id);
        return boardById.modify(boardModifyRequest);
    }

    public void delete(Long id) {
        Board board = boardRepository.findBoardById(id);
        boardRepository.delete(board);

    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public void add_HeartCount(Long id) {
        Board boardById = boardRepository.findBoardById(id);
        boardById.updateHeartCount();
    }

}
