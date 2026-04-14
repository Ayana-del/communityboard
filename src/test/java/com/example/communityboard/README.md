# 🏘 ちいきボード（CommunityBoard）

地域住民がイベント・お知らせ・助け合い情報を投稿・共有できるWebアプリケーションです。

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Docker](https://img.shields.io/badge/Docker-対応-2496ED)

---

## 📋 アプリ概要

KISが掲げる「お客様の立場に立って考える」「感動と喜びを与える」という理念のもと、
デジタルの力で地域コミュニティのつながりを支援することを目的として開発しました。

### 主な機能

| 機能 | 説明 |
|---|---|
| ユーザー登録・ログイン | Spring Securityによるセッション認証 |
| 投稿一覧・詳細表示 | 未ログインでも閲覧可能 |
| 投稿作成・編集・削除 | ログインユーザーが自分の投稿を管理 |
| カテゴリ絞り込み | イベント・お知らせ・助け合いで絞り込み |
| コメント投稿 | 投稿に対してコメントができる |
| いいね機能 | 投稿にいいね・取り消しができる |

---

## 🛠 技術スタック

| レイヤー | 技術 |
|---|---|
| バックエンド | Java 17 + Spring Boot 3 |
| テンプレート | Thymeleaf |
| フロントエンド | HTML / CSS |
| データベース | MySQL 8.0 |
| 認証 | Spring Security（セッション認証・BCrypt） |
| ORM | Spring Data JPA |
| 環境構築 | Docker / Docker Compose |
| バージョン管理 | Git / GitHub |

---

## 🗂 設計ドキュメント

| ドキュメント | 内容 |
|---|---|
| 要件定義書 | ユーザーストーリー・機能一覧・画面一覧 |
| 基本設計書 | 画面遷移図・URL設計・処理フロー |
| 詳細設計書 | クラス設計・シーケンス図・例外処理設計 |
| DB設計書 | ER図・テーブル定義書・DDL |

---

## 🚀 環境構築・起動手順

### 必要な環境

- Java 17以上
- Maven
- Docker / Docker Compose

### 手順

**1. リポジトリをクローン**
```bash
git clone https://github.com/（あなたのGitHubユーザー名）/communityboard.git
cd communityboard
```

**2. MySQLコンテナを起動**
```bash
docker compose up -d
```

起動確認（healthyになれば成功）
```bash
docker compose ps
```

**3. Spring Bootを起動**
```bash
./mvnw spring-boot:run
```

**4. ブラウザでアクセス**  
http://localhost:8080/posts  
  
---

## 👤 サンプルアカウント

| 項目 | 値 |
|---|---|
| メールアドレス | taro@example.com |
| パスワード | password123 |

---

## 📁 ディレクトリ構成  
communityboard/
├── docker/
│   └── init.sql                  # DB初期化SQL
├── docker-compose.yml            # Docker設定
├── pom.xml                       # Maven依存関係
└── src/main/
├── java/com/example/communityboard/
│   ├── controller/           # リクエスト処理
│   ├── service/              # ビジネスロジック
│   ├── repository/           # DBアクセス
│   ├── entity/               # JPAエンティティ
│   ├── dto/                  # フォームDTO
│   └── security/             # Spring Security設定
└── resources/
├── templates/            # Thymeleafテンプレート
│   ├── layout/           # 共通レイアウト
│   ├── posts/            # 投稿画面
│   ├── auth/             # 認証画面
│   └── error/            # エラー画面
├── static/css/           # スタイルシート
└── application.properties # アプリ設定  
  
---

## 💡 工夫したポイント

### 1. 設計ドキュメントの整備
要件定義書・基本設計書・詳細設計書・DB設計書を作成し、
上流工程から意識した開発を実践しました。

### 2. Spring MVCのレイヤー分離
Controller・Service・Repositoryを明確に分離し、
保守性の高いアーキテクチャを採用しました。

### 3. Spring Securityによる認証・認可
BCryptによるパスワードハッシュ化と、
投稿者本人のみ編集・削除できる権限管理を実装しました。

### 4. Docker環境による再現性
Docker Composeで環境を定義し、
コマンド1つで誰でも同じ環境を再現できるようにしました。

### 5. KISの企業理念との一致
「地域のつながりをデジタルで支える」というテーマは、
KISが目指す地域DX・スマートシティの方向性と一致しています。

---

## 📝 今後の拡張予定

- 画像アップロード機能
- 管理者機能（全投稿の管理）
- メール通知機能
- レスポンシブデザインの強化

