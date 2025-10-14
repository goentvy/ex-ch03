package com.example.demo.controller;

import com.example.demo.dto.MemberResponseDto;
import com.example.demo.dto.MemberUpdateDto;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> getMyInfo() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(memberService.getMemberInfo(email));
    }

    // 테스트용: 이메일을 직접 전달 (실제 서비스에서는 인증 정보에서 추출)
    @GetMapping("/{email}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable String email){
        return ResponseEntity.ok(memberService.getMemberInfo(email));
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateMember(@PathVariable String email,
                                          @RequestBody @Valid MemberUpdateDto updateDto) {
        memberService.updateMember(email, updateDto);
        return ResponseEntity.ok("회원 정보 수정 완료");
    }
}
