package com.example.communityboard.dto;

import com.example.communityboard.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {

    @NotBlank(message = "タイトルは必須です")
    @Size(max = 100, message = "タイトルは100文字以内で入力してください")
    private String title;

    @NotBlank(message = "本文は必須です")
    @Size(max = 2000, message = "本文は2000文字以内で入力してください")
    private String body;

    @NotNull(message = "カテゴリを選択してください")
    private Category category;
}