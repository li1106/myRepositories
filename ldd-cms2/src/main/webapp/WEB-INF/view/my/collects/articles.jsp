<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- ${articles } --%>
	<div id="content-wrapper">
		<ul class="list-unstyled">
			<c:forEach items="${articles }" var="t">
				<li class="media"><a
					href="javascript:mopen(${t.id },${t.channelId })"><img
						class="mr-3" src="/pic/${t.picture}" alt="no pic" width="80px"
						height="80px"></a>
					<div class="media-body">
						<dl>
							<dt style="font-size: 18px">
								<a href="javascript:mopen(${t.id },${t.channelId })">${t.title }</a>
								<input type="button" value="查看" class="btn btn-info"
									onclick="selectArticleById(${t.id})" />
							</dt>
							<dd style="padding-top: 50px">发布时间:${t.updated}</dd>
						</dl>
					</div></li>
				<hr
					style="height: 1px; border: none; border-top: 1px dashed #0066CC;" />
			</c:forEach>
			${pages }
		</ul>

	</div>
	<script type="text/javascript">
		//获取分页连接的URL .并进行加载
		$(function() {
			$('.page-link').click(function(e) {
				//获取点击的的url
				var url = $(this).attr('data');

				//在中间区域显示地址的内容
				$('#content-wrapper').load(url);
			});

		})

		function qx(){
			var flag = $("#qx").prop("checked")
			$("[name='ckb']").each(function(){
				$(this).attr("checked",flag)
			})
		}

			function selectArticleById(id){
				window.location.href="${pageContext.request.contextPath}/article/get?id="+id;
				
			}
	</script>
</body>
</html>