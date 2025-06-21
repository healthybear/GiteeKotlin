package com.healthybear.giteekotlin.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author: 郭健鸿
 * @Date: 2025-06-15 13:03
 * @Description:仓库搜索响应结果
 * @property assignee 代码审查相关的用户分配状态（布尔值列表，可能表示是否分配）
 * @property assignees_number 代码审查设置中的审查人数
 * @property assigner 代码审查的指派者信息
 * @property can_comment 是否允许用户对仓库进行评论
 * @property blobs_url Blob资源URL
 * @property branches_url 分支列表URL
 * @property collaborators_url 协作者列表URL
 * @property comments_url 评论列表URL
 * @property commits_url 提交记录URL
 * @property contributors_url 贡献者列表URL
 * @property created_at 仓库创建时间
 * @property default_branch 默认分支名称
 * @property description 仓库描述
 * @property enterprise 所属企业信息（Namespace类型）
 * @property fork 是否为Fork仓库
 * @property forks_count Fork数量
 * @property forks_url Fork列表URL
 * @property full_name 仓库完整名称（命名空间/仓库名）
 * @property gvp 是否为GVP仓库
 * @property has_issues 是否开启Issue功能
 * @property has_page 是否开启Pages功能
 * @property has_wiki 是否开启Wiki功能
 * @property homepage 仓库主页URL
 * @property hooks_url WebHook列表URL
 * @property html_url 仓库网页地址
 * @property human_name 仓库显示名称
 * @property id 仓库ID
 * @property internal 是否为内部开源仓库（Kotlin关键字需用反引号，JSON字段为"internal"）
 * @property issue_comment 是否允许对已关闭的Issue进行评论
 * @property issue_comment_url Issue评论URL
 * @property issue_template_source Issue模板来源（project/enterprise）
 * @property issues_url Issue列表URL
 * @property keys_url 公钥列表URL
 * @property labels_url 标签列表URL
 * @property language 主要编程语言
 * @property license 开源许可协议名称
 * @property members 仓库成员列表（用户名）
 * @property milestones_url 里程碑列表URL
 * @property name 仓库名称
 * @property namespace 所属命名空间（企业/组织/用户）
 * @property notifications_url 通知列表URL
 * @property open_issues_count 未解决的Issue数量
 * @property outsourced 仓库类型（内部/外包）
 * @property owner 仓库所有者信息
 * @property paas PAAS相关信息（可能为云服务标识）
 * @property parent 父仓库信息（若为Fork仓库）
 * @property path 仓库路径（命名空间路径+仓库名）
 * @property permission 操作权限映射（结构需根据实际数据解析）
 * @property private 是否为私有仓库
 * @property public 是否为公开仓库
 * @property pulls_url Pull Request列表URL
 * @property pushed_at 最近一次推送时间
 * @property recommend 是否为推荐仓库
 * @property releases_url 版本发布列表URL
 * @property ssh_url SSH克隆地址
 * @property stared 当前用户是否Star了该仓库
 * @property stargazers_count Star数量
 * @property stargazers_url Star用户列表URL
 * @property status 仓库状态（如正常/归档等）
 * @property tags_url Tag列表URL
 * @property testers 代码测试相关的用户分配状态（布尔值列表）
 * @property testers_number 代码审查设置中的测试人数
 * @property updated_at 仓库最后更新时间
 * @property url 仓库API地址
 * @property watched 当前用户是否Watch了该仓库
 * @property watchers_count 关注者数量
 * @property project_labels 项目标签列表（结构不明确，暂用Any）
 * @property relation 当前用户与仓库的角色关系（如owner/contributor等）
 * @property programs 未知功能模块（可能为扩展程序）
 */
@JsonClass(generateAdapter = true)
data class SearchRepositoriesResult (
    @Json(name = "assignee")
    val assignee: List<Boolean>, // 代码审查用户分配状态
    @Json(name = "assignees_number")
    val assignees_number: Int, // 审查人数
    @Json(name = "assigner")
    val assigner: Assigner, // 指派者信息
    @Json(name = "can_comment")
    val can_comment: Boolean, // 是否允许评论
    @Json(name = "blobs_url")
    val blobs_url: String, // Blob资源地址
    @Json(name = "branches_url")
    val branches_url: String, // 分支列表地址
    @Json(name = "collaborators_url")
    val collaborators_url: String, // 协作者列表地址
    @Json(name = "comments_url")
    val comments_url: String, // 评论列表地址
    @Json(name = "commits_url")
    val commits_url: String, // 提交记录地址
    @Json(name = "contributors_url")
    val contributors_url: String, // 贡献者列表地址
    @Json(name = "created_at")
    val created_at: String, // 创建时间
    @Json(name = "default_branch")
    val default_branch: String, // 默认分支
    @Json(name = "description")
    val description: String, // 仓库描述
    @Json(name = "enterprise")
    val enterprise: Enterprise, // 所属企业
    @Json(name = "fork")
    val fork: Boolean, // 是否为Fork
    @Json(name = "forks_count")
    val forks_count: Int, // Fork数量
    @Json(name = "forks_url")
    val forks_url: String, // Fork列表地址
    @Json(name = "full_name")
    val full_name: String, // 完整名称
    @Json(name = "gvp")
    val gvp: Boolean, // 是否GVP仓库
    @Json(name = "has_issues")
    val has_issues: Boolean, // 是否开启Issue
    @Json(name = "has_page")
    val has_page: Boolean, // 是否开启Pages
    @Json(name = "has_wiki")
    val has_wiki: Boolean, // 是否开启Wiki
    @Json(name = "homepage")
    val homepage: String, // 主页地址
    @Json(name = "hooks_url")
    val hooks_url: String, // WebHook地址
    @Json(name = "html_url")
    val html_url: String, // 网页地址
    @Json(name = "human_name")
    val human_name: String, // 显示名称
    @Json(name = "id")
    val id: Int, // 仓库ID
    @Json(name = "internal") // Kotlin关键字，JSON字段为"internal"
    val `internal`: Boolean, // 是否内部开源
    @Json(name = "issue_comment")
    val issue_comment: Boolean, // 是否允许评论已关闭Issue
    @Json(name = "issue_comment_url")
    val issue_comment_url: String, // Issue评论地址
    @Json(name = "issue_template_source")
    val issue_template_source: String, // Issue模板来源
    @Json(name = "issues_url")
    val issues_url: String, // Issue列表地址
    @Json(name = "keys_url")
    val keys_url: String, // 公钥列表地址
    @Json(name = "labels_url")
    val labels_url: String, // 标签列表地址
    @Json(name = "language")
    val language: String, // 编程语言
    @Json(name = "license")
    val license: String, // 许可协议
    @Json(name = "members")
    val members: List<String>, // 成员列表
    @Json(name = "milestones_url")
    val milestones_url: String, // 里程碑地址
    @Json(name = "name")
    val name: String, // 仓库名
    @Json(name = "namespace")
    val namespace: Namespace, // 命名空间信息
    @Json(name = "notifications_url")
    val notifications_url: String, // 通知地址
    @Json(name = "open_issues_count")
    val open_issues_count: Int, // 未解决Issue数
    @Json(name = "outsourced")
    val outsourced: Boolean, // 仓库类型（外包/内部）
    @Json(name = "owner")
    val owner: Owner, // 所有者信息
    @Json(name = "paas")
    val paas: String, // PAAS标识
    @Json(name = "parent")
    val parent: Parent, // 父仓库信息
    @Json(name = "path")
    val path: String, // 仓库路径
    @Json(name = "permission")
    val permission: Map<String, Any>, // 权限映射（需根据实际数据调整）
    @Json(name = "private") // JSON字段为"private"，Kotlin中可直接使用
    val `private`: Boolean, // 是否私有
    @Json(name = "public")
    val `public`: Boolean, // 是否公开（Kotlin中public为关键字，但此处字段名加引号即可）
    @Json(name = "pulls_url")
    val pulls_url: String, // PR列表地址
    @Json(name = "pushed_at")
    val pushed_at: String, // 最近推送时间
    @Json(name = "recommend")
    val recommend: Boolean, // 是否推荐仓库
    @Json(name = "releases_url")
    val releases_url: String, // 版本发布地址
    @Json(name = "ssh_url")
    val ssh_url: String, // SSH克隆地址
    @Json(name = "stared")
    val stared: Boolean, // 是否已Star
    @Json(name = "stargazers_count")
    val stargazers_count: Int, // Star数
    @Json(name = "stargazers_url")
    val stargazers_url: String, // Star用户地址
    @Json(name = "status")
    val status: String, // 仓库状态
    @Json(name = "tags_url")
    val tags_url: String, // Tag列表地址
    @Json(name = "testers")
    val testers: List<Boolean>, // 测试用户分配状态
    @Json(name = "testers_number")
    val testers_number: Int, // 测试人数
    @Json(name = "updated_at")
    val updated_at: String, // 最后更新时间
    @Json(name = "url")
    val url: String, // API地址
    @Json(name = "watched")
    val watched: Boolean, // 是否已Watch
    @Json(name = "watchers_count")
    val watchers_count: Int, // 关注数
    @Json(name = "project_labels")
    val project_labels: List<Any>, // 项目标签（结构未知）
    @Json(name = "relation")
    val relation: String, // 用户角色关系
    @Json(name = "programs")
    val programs: List<Boolean> // 未知功能模块
)

/**
 * 代码审查指派者信息
 * @property id 用户ID
 * @property login 登录名
 * @property name 用户名
 * @property avatar_url 头像地址
 * @property url 用户API地址
 * @property html_url 用户网页地址
 * @property remark 企业备注名（可选）
 * @property followers_url 关注者列表地址
 * @property following_url 关注的用户列表地址
 * @property gists_url 代码片段列表地址
 * @property starred_url Starred仓库列表地址
 * @property subscriptions_url 关注的仓库列表地址
 * @property organizations_url 所属组织列表地址
 * @property repos_url 仓库列表地址
 * @property events_url 事件列表地址
 * @property received_events_url 接收的事件列表地址
 * @property type 用户类型（user/organization等）
 * @property member_role 成员角色（如owner/maintainer等）
 */
@JsonClass(generateAdapter = true)
data class Assigner(
    @Json(name = "id") val id: Int,
    @Json(name = "login") val login: String,
    @Json(name = "name") val name: String,
    @Json(name = "avatar_url") val avatar_url: String,
    @Json(name = "url") val url: String,
    @Json(name = "html_url") val html_url: String,
    @Json(name = "remark") val remark: String? = null, // 可选字段
    @Json(name = "followers_url") val followers_url: String,
    @Json(name = "following_url") val following_url: String,
    @Json(name = "gists_url") val gists_url: String,
    @Json(name = "starred_url") val starred_url: String,
    @Json(name = "subscriptions_url") val subscriptions_url: String,
    @Json(name = "organizations_url") val organizations_url: String,
    @Json(name = "repos_url") val repos_url: String,
    @Json(name = "events_url") val events_url: String,
    @Json(name = "received_events_url") val received_events_url: String,
    @Json(name = "type") val type: String,
    @Json(name = "member_role") val member_role: String
)

/**
 * 命名空间信息（企业/组织/用户）
 * @property id 命名空间ID
 * @property type 类型（Enterprise/Group/null）
 * @property name 名称
 * @property path 路径
 * @property html_url 网页地址
 */
@JsonClass(generateAdapter = true)
data class Enterprise(
    @Json(name = "id") val id: Int,
    @Json(name = "type") val type: String,
    @Json(name = "name") val name: String,
    @Json(name = "path") val path: String,
    @Json(name = "html_url") val html_url: String
)

// Namespace类与Enterprise类结构相同，可能为同一概念，此处复用或重命名
@JsonClass(generateAdapter = true)
data class Namespace(
    @Json(name = "id") val id: Int,
    @Json(name = "type") val type: String,
    @Json(name = "name") val name: String,
    @Json(name = "path") val path: String,
    @Json(name = "html_url") val html_url: String
)

/**
 * 仓库所有者信息（用户/组织）
 * 结构与Assigner类似，可能可合并，但按原数据单独定义
 */
@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "id") val id: Int,
    @Json(name = "login") val login: String,
    @Json(name = "name") val name: String,
    @Json(name = "avatar_url") val avatar_url: String,
    @Json(name = "url") val url: String,
    @Json(name = "html_url") val html_url: String,
    @Json(name = "remark") val remark: String? = null,
    @Json(name = "followers_url") val followers_url: String,
    @Json(name = "following_url") val following_url: String,
    @Json(name = "gists_url") val gists_url: String,
    @Json(name = "starred_url") val starred_url: String,
    @Json(name = "subscriptions_url") val subscriptions_url: String,
    @Json(name = "organizations_url") val organizations_url: String,
    @Json(name = "repos_url") val repos_url: String,
    @Json(name = "events_url") val events_url: String,
    @Json(name = "received_events_url") val received_events_url: String,
    @Json(name = "type") val type: String,
    @Json(name = "member_role") val member_role: String
)

/**
 * 父仓库信息（若为Fork）
 * 包含完整的仓库信息嵌套
 */
@JsonClass(generateAdapter = true)
data class Parent(
    @Json(name = "id") val id: Int,
    @Json(name = "full_name") val full_name: String,
    @Json(name = "human_name") val human_name: String,
    @Json(name = "url") val url: String,
    @Json(name = "namespace") val namespace: Namespace,
    @Json(name = "path") val path: String,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: Owner,
    @Json(name = "assigner") val assigner: Assigner,
    @Json(name = "description") val description: String,
    @Json(name = "private") val `private`: Boolean,
    @Json(name = "public") val `public`: Boolean,
    @Json(name = "internal") val `internal`: Boolean,
    @Json(name = "fork") val fork: Boolean,
    @Json(name = "html_url") val html_url: String,
    @Json(name = "ssh_url") val ssh_url: String,
    @Json(name = "forks_url") val forks_url: String,
    @Json(name = "keys_url") val keys_url: String,
    @Json(name = "collaborators_url") val collaborators_url: String,
    @Json(name = "hooks_url") val hooks_url: String,
    @Json(name = "branches_url") val branches_url: String,
    @Json(name = "tags_url") val tags_url: String,
    @Json(name = "blobs_url") val blobs_url: String,
    @Json(name = "stargazers_url") val stargazers_url: String,
    @Json(name = "contributors_url") val contributors_url: String,
    @Json(name = "commits_url") val commits_url: String,
    @Json(name = "comments_url") val comments_url: String,
    @Json(name = "issue_comment_url") val issue_comment_url: String,
    @Json(name = "issues_url") val issues_url: String,
    @Json(name = "pulls_url") val pulls_url: String,
    @Json(name = "milestones_url") val milestones_url: String,
    @Json(name = "notifications_url") val notifications_url: String,
    @Json(name = "labels_url") val labels_url: String,
    @Json(name = "releases_url") val releases_url: String,
    @Json(name = "recommend") val recommend: Boolean,
    @Json(name = "gvp") val gvp: Boolean,
    @Json(name = "homepage") val homepage: String,
    @Json(name = "language") val language: String,
    @Json(name = "forks_count") val forks_count: Int,
    @Json(name = "stargazers_count") val stargazers_count: Int,
    @Json(name = "watchers_count") val watchers_count: Int,
    @Json(name = "default_branch") val default_branch: String,
    @Json(name = "open_issues_count") val open_issues_count: Int,
    @Json(name = "has_issues") val has_issues: Boolean,
    @Json(name = "has_wiki") val has_wiki: Boolean,
    @Json(name = "issue_comment") val issue_comment: Boolean,
    @Json(name = "can_comment") val can_comment: Boolean,
    @Json(name = "pull_requests_enabled") val pull_requests_enabled: Boolean,
    @Json(name = "has_page") val has_page: Boolean,
    @Json(name = "license") val license: String,
    @Json(name = "outsourced") val outsourced: Boolean,
    @Json(name = "project_creator") val project_creator: String,
    @Json(name = "members") val members: List<String>,
    @Json(name = "pushed_at") val pushed_at: String,
    @Json(name = "created_at") val created_at: String,
    @Json(name = "updated_at") val updated_at: String,
    @Json(name = "paas") val paas: String,
    @Json(name = "stared") val stared: Boolean,
    @Json(name = "watched") val watched: Boolean,
    @Json(name = "permission") val permission: Map<String, Any>,
    @Json(name = "relation") val relation: String,
    @Json(name = "assignees_number") val assignees_number: Int,
    @Json(name = "testers_number") val testers_number: Int,
    @Json(name = "status") val status: String,
    @Json(name = "programs") val programs: List<Boolean>,
    @Json(name = "project_labels") val project_labels: List<Any>,
    @Json(name = "issue_template_source") val issue_template_source: String
)