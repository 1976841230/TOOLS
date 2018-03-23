<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>文件上传</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- ZUI 标准版压缩后的 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/commoncss/zui.min.css">
    <link href="${pageContext.request.contextPath}/css/commoncss/zui.uploader.min.css" rel="stylesheet">
    <!-- ZUI Javascript 依赖 jQuery -->
    <script src="${pageContext.request.contextPath}/js/commonjs/jquery-1.11.3.min.js" type="text/javascript"></script>
    <!-- ZUI 标准版压缩后的 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/js/commonjs/zui.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/commonjs/zui.uploader.min.js" type="text/javascript"></script>
</head>

<body>

<!-- 对话框HTML -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
                <h4 class="modal-title">识别结果</h4>
            </div>
            <div class="modal-body">
                <p id="recognition-content"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div style="margin:0 auto; width: 60%;" id="test">

    <h2>图片上传 支持拖拽上传</h2>

    <div class="uploader" id="uploaderExample">
        <div class="uploader-message">
            <div class="content"></div>
            <button type="button" class="close">×</button>
        </div>
        <div class="uploader-files file-list file-list-lg" data-drag-placeholder="请拖拽文件到此处"></div>
        <div class="uploader-actions">
            <div class="uploader-status pull-right text-muted"></div>
            <button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i>选择文件</button>
            <button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i> 开始上传</button>
        </div>
    </div>

</div>

<script type="text/javascript">

    $(function () {

        var msgArr = [];
        $('#uploaderExample').uploader({
                                           url: '${pageContext.request.contextPath}/upload/image',
                                           autoUpload: false,
                                           max_retries: 2,
                                           chunk_size: 0,
                                           responseHandler: function (responseObject, file) {
                                               // 将后端传来的Json字符串转换为JS对象
                                               var msg = JSON.parse(responseObject.response);
                                               msgArr.push(msg);
                                               recognitionContent(msg);
                                               var options = {
                                                   moveable: true
                                               };
                                               $("#myModal").modal(options);
                                               clickImageIcon(msgArr, options);
                                           }
                                       });

    });

    /**
     * 将文字识别结果添加到对话框中
     * @param msg
     */
    function recognitionContent(msg) {
        var recognition_content = $('#recognition-content');
        recognition_content.empty();
        for (var word = 0; word < msg.words_result.length; word++) {
            recognition_content.append('<span>' + msg.words_result[word].words + '</span><br/>');
        }
    }

    /**
     * 为每张图片绑定点击事件
     * @param msgArr 多张图片的识别结果保存到数组 msgArr 中
     * @param options 对话框参数配置
     */
    function clickImageIcon(msgArr, options) {
        for (var i = 0; i < msgArr.length; i++) {
            (function (index) {
                $('.file-wrapper:eq(' + index + ')').bind('click', function () {
                    recognitionContent(msgArr[index]);
                    $('#myModal').modal(options);
                });
            })(i);
        }
    }

</script>
</body>
</html>