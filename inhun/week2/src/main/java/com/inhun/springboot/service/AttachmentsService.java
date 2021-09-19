package com.inhun.springboot.service;

import com.inhun.springboot.model.Attachments;
import com.inhun.springboot.model.AttachmentsRepository;
import org.springframework.stereotype.Service;

@Service
public class AttachmentsService {
    private final AttachmentsRepository attachmentsRepository;

    public AttachmentsService(AttachmentsRepository attachmentsRepository) {
        this.attachmentsRepository = attachmentsRepository;
    }

    public Attachments save(Attachments attachment) {
        attachmentsRepository.save(attachment);
        return attachment;
    }
}
