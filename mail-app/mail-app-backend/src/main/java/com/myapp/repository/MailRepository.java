package com.myapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myapp.models.Mail;

public interface MailRepository extends MongoRepository<Mail, String> {
  Optional<List<Mail>> findByUploadedBy(String username);
}