package com.example.communityboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {

    @NotBlank(message = "コメントを入力してください")
    @Size(max = 500, message = "コメントは500文字以内で入力してください")
    private String body;
}