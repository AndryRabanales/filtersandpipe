package com.mycompany.kwic.business;
import com.mycompany.kwic.model.KwicRequest;

public interface IKwicService {
    void runKwicProcess(KwicRequest request) throws Exception;
}
