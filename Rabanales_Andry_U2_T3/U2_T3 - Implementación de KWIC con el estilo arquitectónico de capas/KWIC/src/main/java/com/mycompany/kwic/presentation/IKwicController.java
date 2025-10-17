package com.mycompany.kwic.presentation;
import com.mycompany.kwic.model.KwicRequest;

public interface IKwicController {
    void execute(KwicRequest request) throws Exception;
}