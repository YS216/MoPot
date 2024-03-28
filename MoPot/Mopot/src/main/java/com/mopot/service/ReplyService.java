package com.mopot.service;

import com.mopot.domain.Reply;
import com.mopot.repository.ContentRepository;
import com.mopot.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    ContentRepository contentRepository;

    public List<Reply> findAllByRefCnoOrderByReNoDesc(Long conNo){
        return replyRepository.findAllByRefCnoOrderByReNoDesc(conNo);
    }

    /* 댓글 입력하기 */
    public Reply insertReplyForm(Reply reply) {
        return replyRepository.save(reply);
    }

    /* 댓글 수정하기 - 값을 한번에 댓글번호, 글번호, 댓글모델 3개를 받아 갖고 온다 */
    public Reply updateReply(@PathVariable(name = "reNo") long reNo,
                             @PathVariable(name = "conNo") long conNo,
                             @ModelAttribute Reply reply) {
        Reply rReply = replyRepository.findById(reply.getReNo()).orElse(null);
        rReply.setReDetail(reply.getReDetail());
        return replyRepository.save(rReply);
    }

    /* 댓글 삭제하기 - 값을 한번에 글번호, 댓글번호 2개를 받아 갖고 온다  */
    public void replyDelete(long reNo, long conNo)throws Exception {
        replyRepository.deleteById((long)reNo);

    }

    /* 대댓글 입력하기 */
    @Transactional
    public Reply insertReplyReply(Reply reply) {
        return replyRepository.save(reply);
    }


}
