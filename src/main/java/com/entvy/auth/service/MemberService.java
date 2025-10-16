package com.entvy.auth.service;

import com.entvy.auth.dto.MemberResponseDto;
import com.entvy.auth.dto.MemberUpdateDto;
import com.entvy.auth.entity.Member;
import com.entvy.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto getMemberInfo(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        MemberResponseDto dto = new MemberResponseDto();
        dto.setEmail(member.getEmail());
        dto.setName(member.getName());
        return dto;
    }

    public void updateMember(String email, MemberUpdateDto updateDto) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        member.setName(updateDto.getName());
        member.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        memberRepository.save(member);
    }
}
