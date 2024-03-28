package com.mopot.controller;

import com.mopot.domain.Reply;
import com.mopot.service.ContentService;
import com.mopot.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @Autowired
    ContentService contentService;

    /* 댓글 입력하기 */
    @PostMapping("/insertReplyForm.bo")
    @ResponseBody
    public Reply insertReplyForm(Reply reply) {
        return replyService.insertReplyForm(reply);
    }

    /* 댓글 수정하기 - 값을 한번에 댓글번호, 글번호, 댓글모델 3개를 받아 갖고 온다 */
    @PostMapping("/updateReply/{reNo}/{conNo}")
    public String updateReply(@PathVariable(name = "reNo") long reNo,
                              @PathVariable(name = "conNo") long conNo,
                              @ModelAttribute Reply reply
    ) throws Exception{
        replyService.updateReply(reNo, conNo, reply);
        return "redirect:/detailForm?conNo=" + conNo;
    }

    /* 댓글 삭제하기 - 값을 한번에 글번호, 댓글번호 2개를 받아 갖고 온다 */
    @RequestMapping("/replyDelete/{reNo}/{conNo}")
    public String replyDelete(@PathVariable(name = "reNo") long reNo,
                              @PathVariable(name = "conNo") long conNo
    ) throws Exception{
        replyService.replyDelete(reNo, conNo);
        return "redirect:/detailForm?conNo=" + conNo;
    }

    /* 대댓글 입력하기 */
    @PostMapping("/insertReplyReply.bo")
    @ResponseBody
    public Reply insertReplyReply(Reply reply) {
        return replyService.insertReplyReply(reply);
    }



}
