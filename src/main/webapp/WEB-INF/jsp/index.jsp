<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/head.jsp"%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#uploadImageModal">上传文件</button>
  <!-- 模态框（Modal） -->
  <div class="modal fade" id="uploadImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title text-center" id="uploadImageModalLabel">上传图像</h4>
        </div>
        <div class="modal-body">
          <form class="bs-example bs-example-form" role="form">
            <div class="row">
                <div class = "col-lg-9">
                    <div class="input-group">
                        <input type="text" class="form-control col-lg-offset-3 col-lg-4" />
                        <span class="input-group-btn">
                             <button class="btn btn-success " type="button" id = "choseFile">选择文件</button>
                        </span>
                      <input type="file" id="inputfile" name = "image" style="display:none"/>
                    </div><!-- /input-group -->
                </div>
            </div><!-- /.row -->
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary">上传</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
  </div>
  <form method="post" , enctype="multipart/form-data" , action="/image/upload">
      <input type="file" name="image"/>
      <input type="text" name="category"/>
      <input type="submit" name="提交" />
  </form>

  </body>
<script src="/resources/script/index.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
        image.init() ;
    }) ;
</script>
</html>
