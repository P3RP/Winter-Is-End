package com.inhun.springboot.service;

import com.inhun.springboot.model.Attachment;
import com.inhun.springboot.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;



    public Attachment save(Attachment attachment) {
        attachmentRepository.save(attachment);
        return attachment;
    }
}
