package com.question.dto;

import com.question.entity.Bank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBankDto {

    private String name;

    private Integer userId;

    private String scroe;
    //题库集合一个问卷下面有很多题的集合
    List<Bank> bankList;
}
