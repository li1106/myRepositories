<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>文章详情</title>
<script type="text/javascript">
	$(function() {
		// 更新点击量
		updateHit();
	})

	function updateHit() {
		// 获取DOM对象
		var articleDom = document.getElementById("articleId");
		// 将DOM对象转换成jQuery对象的方式   ${articleDOM}
		// 将jQuery对象转换成dom对象
		var articleIdDom = $("articleId")[0];
		// 获取自定义属性的值 data-id   
		var id = articleIdDom.dataset.id;
		$.post("${pageContext.request.contextPath}/article/updateHit", {
			id : id
		}, function(flag) {
		}, "JSON")
	}
</script>

</head>
<body>
	<%-- ${map } --%>
	<div class="container" align="center">
		<dl>
			<dt>
				<h2 id="articleId" date-id="${map.id }">${map.title }</h2>
				<h5>
					作者:${map.nickname } 发布时间:
					<fmt:formatDate value="${map.updated }"
						pattern="yyyy-MM-dd HH:mm:ss" />
					点击量:${map.hits } <span class="glyphicon glyphicon-star-empty"
						data-article-id="${map.id }" onclick="collectArticle(this)"
						data-user-id="${sessionScope.LOGIN_GENERAL==null?0:sessionScope.LOGIN_GENERAL.id}"></span>

					<span class="glyphicon glyphicon-star" onclick="disCollect()"></span>
				</h5>
			</dt>
			<dd>${map.content }</dd>
		</dl>
	</div>
</body>
</html>