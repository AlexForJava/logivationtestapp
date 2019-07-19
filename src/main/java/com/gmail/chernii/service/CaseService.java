package com.gmail.chernii.service;

import com.gmail.chernii.model.Case;
import com.gmail.chernii.model.Orderline;

import java.util.List;

public interface CaseService {
    Case solution(List<Case> caseList, List<Orderline> orderlineList);
}
