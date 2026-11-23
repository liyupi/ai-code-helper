# 🤖 AI 编程小助手（ai-code-helper）

基于 Spring Boot 3.5、Java 21、LangChain4j 与 Vue 3 构建的开源 AI 编程学习与求职辅导助手，由 [程序员鱼皮](http://dogyupi.com/) 原创出品，通过一个结构精炼的全栈项目带你系统掌握 LangChain4j 核心开发范式。

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Vue.js](https://img.shields.io/badge/Vue.js-3.3.4-4FC08D.svg)](https://vuejs.org/)
[![LangChain4j](https://img.shields.io/badge/LangChain4j-1.1.0-blue.svg)](https://github.com/langchain4j/langchain4j)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)

[项目介绍](#-项目介绍) · [核心特性](#-核心特性) · [快速开始](#-快速开始) · [技术架构与核心模块](#-技术架构与核心模块) · [生态学习资源](#-生态学习资源) · [常见问题](#-常见问题) · [致谢](#-致谢)

> 大家好，我是 [程序员鱼皮](http://dogyupi.com/)。如今 AI 应用开发已经成为程序员求职与进阶的必备技能。继使用 Spring AI 开源打造 [AI 超级智能体项目（yu-ai-agent）](https://github.com/liyupi/yu-ai-agent) 之后，我专门设计了这套小而精的实战项目，拒绝枯燥的纯理论灌输，通过从零搭建一个实用的编程与求职小助手，带大家一站式吃透 Java 生态另一大主流 AI 框架 LangChain4j。学完这套教程后，你不仅能熟练运用 LangChain4j，还能为简历增加一段扎实的 AI 全栈项目经历。
>
> 配套学习入口：观看 [B 站完整视频教程](https://bilibili.com/video/BV1X4GGziEyr)，阅读 [公众号详细文字教程](https://mp.weixin.qq.com/s/7cNh7ndeiWiHBjnkTkz_Zg)，或者直接前往 [编程导航 AI 编程助手课程主页](https://www.codefather.cn/course/1943267371799080961) 与 [编程导航学习网](https://www.codefather.cn/) 获取更多实战教程与编程学习路线。如果这个项目对你有帮助，欢迎点亮右上角的 Star 支持一下，这会激励我继续输出更多高质量开源教程！

![AI 编程小助手 LangChain4j 全栈实战项目功能界面展示](https://pic.yupi.icu/1/AI%E7%BC%96%E7%A8%8B%E5%B0%8F%E5%8A%A9%E6%89%8B%E9%A1%B9%E7%9B%AE.png)

## ✨ 项目介绍

`ai-code-helper` 聚焦程序员日常学习与求职备战的真实痛点，将对话记忆、结构化输出、RAG 知识库检索增强、Tool Calling 工具调用、MCP 模型上下文协议、安全护轨（Guardrail）、可观测性监听以及前端流式交互等企业级能力全部浓缩进一个清晰易懂的工程之中，非常适合作为 Java 开发者入门 AI 应用开发的第一站。

在实际使用中，这个智能助手同时承担了三重角色。作为编程学习导师，它能够结合学习者的技术基础规划清晰的学习路线并提供个性化建议；作为求职面试助手，它可以协助优化简历亮点、拆解面试技巧并动态搜索高频面试题目；作为代码答疑专家，它还能实时响应日常编程报错与技术难题，直接给出可运行的参考代码示例。

## 🌟 核心特性

为了帮助你快速理解 LangChain4j 在生产项目中的典型落地方式，项目按照「核心 AI 服务、安全防护、外部工具集成」三大维度设计了完备的功能模块。

| 能力维度 | 核心特性与技术实现 |
| --- | --- |
| AI 声明式服务与大模型 | 深度集成 LangChain4j AI Service 声明式开发模式，接入阿里云通义千问（Qwen）等主流大语言模型，支持多轮上下文对话记忆与结构化结果输出 |
| 实时流式交互体验 | 后端通过 SSE（Server-Sent Events）协议推送模型生成的增量内容，搭配 Vue 3 聊天界面与 Markdown 渲染实现丝滑的实时打字机效果 |
| 输入安全护轨机制 | 内置自定义 Guardrail 输入安全护轨，在请求送往大模型之前自动检测敏感词与越权风险，保障企业级 AI 应用的内容安全 |
| RAG 知识库检索增强 | 结合本地学习与求职文档构建向量知识库，在问答前动态检索相关片段注入上下文，有效提升专业领域回答的准确度 |
| 工具调用与网页检索 | 通过 Tool Calling 机制封装面试题搜索工具与 Web 爬虫能力，让大模型按需实时获取外部技术资讯与高频面试题目 |
| MCP 协议与可观测性 | 接入 MCP（Model Context Protocol）模型上下文协议扩展外部工具生态，并通过监听器记录模型调用过程，便于后续调试与监控 |

## 🚀 快速开始

只需要准备好基础开发环境和大模型密钥，几分钟之内就能在本地完整跑通前后端服务并开始体验。

### 环境要求

请先确保本地已经安装 JDK 21 及以上版本、Node.js 16.0 及以上版本以及 Maven 3.6 及以上版本。同时，你需要提前申请 [阿里云百炼通义千问 API Key](https://dashscope.aliyun.com/) 与 Big Model API 密钥，用于驱动对话模型、向量嵌入模型及相关外部服务。

### 1. 启动后端服务

克隆本仓库到本地后，打开 `src/main/resources/application.yml` 填入你的通义千问 API Key 和 Big Model API 密钥，然后通过 Maven 启动 Spring Boot 后端服务。

```bash
# 克隆项目代码
git clone https://github.com/liyupi/ai-code-helper.git
cd ai-code-helper

# 编辑 src/main/resources/application.yml
# 填入你的通义千问 API Key 与 Big Model API 密钥

# 启动后端服务
mvn spring-boot:run
```

### 2. 启动前端服务

新开一个终端窗口进入前端目录，安装依赖并启动 Vue 3 本地开发服务器。

```bash
# 进入前端目录
cd ai-code-helper-frontend

# 安装前端依赖
npm install

# 启动开发服务器
npm run dev
```

### 3. 访问应用

前后端服务均启动成功后，在浏览器中打开 `http://localhost:5173` 即可进入智能对话界面，后端 RESTful 与 SSE 接口默认运行在 `http://localhost:8081/api`。

## 📐 技术架构与核心模块

整个项目采用清晰的前后端分离分层架构，Vue 3 前端负责交互与流式 Markdown 渲染，Spring Boot 后端通过 LangChain4j 统一编排大模型、知识库、工具调用和安全护轨。

```text
┌─────────────────┐    ┌─────────────────┐
│   Vue.js 前端    │────│  Spring Boot   │
│   - 聊天界面     │    │    后端服务      │
│   - 实时流式     │    │   - RESTful API │
│   - Markdown    │    │   - SSE 推送     │
└─────────────────┘    └─────────────────┘
                              │
                    ┌─────────────────┐
                    │   LangChain4j   │
                    │   - AI服务层    │
                    │   - 工具集成    │
                    │   - 安全防护    │
                    └─────────────────┘
                              │
                    ┌─────────────────┐
                    │   通义千问API    │
                    │   - 对话模型    │
                    │   - 嵌入模型    │
                    │   - 流式输出    │
                    └─────────────────┘
```

### 后端核心类与仓库目录

在阅读源码时，你可以顺着下面这些核心类快速理清整条调用链路，项目同时将后端代码与 `ai-code-helper-frontend/` 前端工程放在同一仓库中以便对照学习。

| 模块或类名 | 核心职责说明 |
| --- | --- |
| `AiCodeHelperService` | 基于 LangChain4j AI Service 封装的核心对话服务接口，统一编排对话记忆、流式输出与结构化响应 |
| `QwenChatModelConfig` | 管理通义千问对话模型与参数配置，支持灵活切换和扩展模型实例 |
| `RagConfig` | 负责本地知识库文档加载、分块向量化以及 RAG 检索增强器的装配 |
| `McpConfig` | 配置 MCP 模型上下文协议客户端，使助手能够无缝调用外部标准化工具服务 |
| `InterviewQuestionTool` | 基于爬虫与检索逻辑实现的面试题搜索工具，供大模型在求职问答场景自动触发调用 |
| `SafeInputGuardrail` | 自定义输入安全护轨拦截器，用于校验用户输入内容并阻断违规敏感请求 |
| `ChatModelListener` | 模型调用生命周期监听器，负责日志追踪与可观测性信息采集 |

## 📚 生态学习资源

如果你想在学完这个入门项目后继续深入 AI 应用开发、提升工程效率或冲刺技术面试，可以结合以下由 [程序员鱼皮](http://dogyupi.com/) 团队提供的生态资源同步进阶：

1. 想获取本项目更详细的答疑与配套资料，或者系统跟学几十套企业级全栈与 AI 实战项目（如升级进阶到 [AI 零代码应用生成平台](https://www.codefather.cn/course/1948291549923344386) 或 [AI 超级智能体项目](https://www.codefather.cn/course/1915010091721236482)），可以前往 [编程导航 AI 编程助手课程页](https://www.codefather.cn/course/1943267371799080961) 与 [编程导航官网](https://www.codefather.cn)。
2. 希望借助 AI 编程（Vibe Coding）高效扩展本项目功能、系统学习提示词工程与前沿大模型工具，推荐收藏鱼皮开源的 [AI 编程与大模型指南（ai-guide）](https://github.com/liyupi/ai-guide)，访问 [鱼皮 AI 导航网站](https://ai.codefather.cn)，并参考 [AI 编程实战课程](https://ai.codefather.cn/course/2087008226460565505)。
3. 完成项目实战并准备求职面试时，可以搭配 [程序员面试刷题神器面试鸭](https://www.mianshiya.com/) 刷透 Java、Spring Boot、LangChain4j 与 AI 大模型高频面试题，将项目经验真正转化为面试优势。

## ❓ 常见问题

<details>
<summary><b>Q1：Java 开发者如何零基础快速上手 LangChain4j 框架？</b></summary>

对于熟悉 Spring Boot 的 Java 开发者来说，最有效的方法是直接通过一个完整但体量适中的全栈项目进行实战。`ai-code-helper` 基于 Spring Boot 3.5、Java 21 与 LangChain4j 打造，覆盖了 AI Service、会话记忆、结构化输出、RAG、工具调用、MCP、Guardrail 安全护轨和 SSE 流式响应，跟着配套视频与源码通常只需几天即可系统掌握 LangChain4j 主流用法。

</details>

<details>
<summary><b>Q2：LangChain4j 和 Spring AI 在 Java AI 应用开发中应该怎么选择？</b></summary>

两大框架都是 Java 生态非常成熟的主流选择。Spring AI 与 Spring Boot 生态原生融合更紧密，适合偏好 Spring 官方风格的项目；LangChain4j 迭代活跃、声明式 AI Service 和 Guardrail 护轨等特性十分灵活，非常适合快速构建复杂 AI 编排。你可以先用本仓库 `ai-code-helper` 掌握 LangChain4j，再结合同系列的 [yu-ai-agent](https://github.com/liyupi/yu-ai-agent) 对比学习 Spring AI。

</details>

<details>
<summary><b>Q3：如何在 LangChain4j 中落地 RAG 知识库、MCP 协议与输入安全护轨？</b></summary>

本仓库提供了可直接运行的参考实现：`RagConfig` 演示了文档切片、向量嵌入与检索增强配置；`McpConfig` 演示了如何通过 MCP 协议接入外部工具服务；`SafeInputGuardrail` 则演示了如何在调用大模型前拦截敏感输入。你可以直接阅读对应源码并复用到自己的业务项目中。

</details>

<details>
<summary><b>Q4：有哪些小而精、几天就能学完的 Java AI 全栈开源练手项目？</b></summary>

`ai-code-helper` 专为想快速做出第一段 AI 全栈项目经历的开发者设计，整体结构简洁清晰但技术栈非常完整，包含 Spring Boot 3.5 后端、LangChain4j AI 编排层和 Vue 3 流式聊天前端，并且提供免费 B 站视频教程与图文详解，非常适合新手入门或作为进阶微服务 AI 项目前的热身项目。

</details>

<details>
<summary><b>Q5：运行本项目必须使用通义千问模型吗，能否切换为 DeepSeek 或其他大模型？</b></summary>

项目默认基于阿里云通义千问（Qwen）演示对话与向量嵌入能力，但由于底层通过 LangChain4j 进行抽象封装，只需调整 `QwenChatModelConfig` 与 `application.yml` 中的模型配置及对应依赖，即可平滑切换到 DeepSeek、OpenAI 兼容接口或本地 Ollama 大模型。

</details>

## 🙏 致谢

感谢以下优秀的开源框架与云服务平台为本项目提供坚实的技术支撑：

- [LangChain4j](https://github.com/langchain4j/langchain4j)，功能强大且优雅的 Java AI 应用开发框架。
- [阿里云通义千问](https://dashscope.aliyun.com/)，稳定可靠的大语言模型与向量嵌入服务平台。
- [Spring Boot](https://spring.io/projects/spring-boot)，大幅简化企业级 Java 后端开发的现代框架。
- [Vue.js](https://vuejs.org/)，轻量高效的渐进式 JavaScript 前端框架。

更多由 [程序员鱼皮](http://dogyupi.com/) 团队维护的开源项目、求职攻略与 AI 实战资源，欢迎访问 [鱼皮团队资源大全](http://dogyupi.com/) 查看。
