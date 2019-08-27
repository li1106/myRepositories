<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>文章标题</title>
<script type="text/javascript">
	function mopen(id, channelId) {

		//如果是图片频道
		if (channelId == 9) {
			window.open("/article/selectPic?id=" + id, "_blank")
		} else {//普通文章
			window.open("/article/get?id=" + id, "_blank")
		}

	}
</script>
</head>
<body>
	<%-- ${articles } --%>
	<div id="content-wrapper">
		<ul class="list-unstyled">
			<c:forEach items="${titles }" var="t">
				<li class="media"><a
					href="javascript:mopen(${t.id },${t.channelId })"><img
						class="mr-3" src="/pic/${t.picture}" alt="no pic" width="80px"
						height="80px"></a>
					<div class="media-body">
						<dl>
							<dt style="font-size: 18px">
								<a href="javascript:mopen(${t.id },${t.channelId })">${t.title }</a>
								<input type="button" value="删除" class="btn btn-danger"
									onclick="delArticleById(${t.id})" /> <input type="button"
									value="修改" class="btn btn-auto" /> <input type="button"
									value="查看" class="btn btn-info"
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


		 function deleteArticleByIds(){
			if(confirm("确定要删除所选的数据吗？")){
				 var path="";
				 $("[name='ckb']:checked").each(function(){
					path+=","+$(this).val()
				})
				path = path.substring(1);
				$.post("${pageContext.request.contextPath}/article/deleteArticleByIds",{ids:path},function(flag){
					if(flag){
						alert("操作成功")
						window.location.reload();
					}else{
						alert("操作失败")
					}
				},"JSON")
			}
		} 

			function delArticleById(id){
				if(confirm("确定要删除所选的数据吗？")){
					$.post("${pageContext.request.contextPath}/article/deleteArticleByIds",{ids:id},function(flag){
						if(flag){
							alert("操作成功")
							window.location.reload();
						}else{
							alert("操作失败")
						}
					},"JSON")
				}
			}

			function selectArticleById(id){
				window.location.href="${pageContext.request.contextPath}/article/get?id="+id;
				
			}
	</script>
</body>
</html>