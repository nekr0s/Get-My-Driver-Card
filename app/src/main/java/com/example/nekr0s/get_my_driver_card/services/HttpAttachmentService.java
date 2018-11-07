package com.example.nekr0s.get_my_driver_card.services;

import android.content.Context;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.models.Attachment;
import com.example.nekr0s.get_my_driver_card.repositories.base.Repository;
import com.example.nekr0s.get_my_driver_card.services.base.Service;

import java.io.IOException;
import java.util.List;

public class HttpAttachmentService implements Service<Attachment> {

    private final Repository<Attachment> repository;

    public HttpAttachmentService(Context context) {
        this.repository = GetMyDriverCardApplication.getAttachmentRepository(context);
    }

    @Override
    public List<Attachment> getAll() throws IOException {
        return repository.getAll();
    }

    @Override
    public Attachment getById(int id) throws IOException {
        return repository.getById(id);
    }

    @Override
    public Attachment create(Attachment item) throws Exception {
        return repository.add(item);
    }
}
