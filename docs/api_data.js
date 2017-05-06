define({ "api": [
  {
    "type": "post",
    "url": "/craw",
    "title": "爬取图片",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "url",
            "description": "<p>爬取的url地址</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>爬取的类型</p>"
          }
        ]
      }
    },
    "group": "craw",
    "version": "0.0.0",
    "filename": "web/ImageCrawlerController.java",
    "groupTitle": "craw",
    "name": "PostCraw"
  },
  {
    "type": "post",
    "url": "/craw/v2",
    "title": "爬取图片",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "url",
            "description": "<p>爬取的url地址</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>爬取的类型</p>"
          }
        ]
      }
    },
    "group": "craw",
    "version": "0.0.0",
    "filename": "web/ImageCrawlerController.java",
    "groupTitle": "craw",
    "name": "PostCrawV2"
  },
  {
    "type": "delete",
    "url": "/image/id/:id",
    "title": "删除对应id的图片",
    "group": "image",
    "version": "0.0.0",
    "filename": "web/ImageController.java",
    "groupTitle": "image",
    "name": "DeleteImageIdId"
  },
  {
    "type": "get",
    "url": "/image/:category",
    "title": "查询当前类别下的所有图像",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "category",
            "description": "<p>类别</p>"
          }
        ]
      }
    },
    "group": "image",
    "version": "0.0.0",
    "filename": "web/ImageController.java",
    "groupTitle": "image",
    "name": "GetImageCategory"
  },
  {
    "type": "get",
    "url": "/image/id/:id",
    "title": "获取对应id的图片",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": ""
          }
        ]
      }
    },
    "group": "image",
    "version": "0.0.0",
    "filename": "web/ImageController.java",
    "groupTitle": "image",
    "name": "GetImageIdId"
  },
  {
    "type": "get",
    "url": "/image/type/:type/page/:page",
    "title": "获取对应类型的第几页商品",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "type",
            "description": "<p>类型</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "page",
            "description": "<p>页数</p>"
          }
        ]
      }
    },
    "group": "image",
    "version": "0.0.0",
    "filename": "web/ImageController.java",
    "groupTitle": "image",
    "name": "GetImageTypeTypePagePage"
  },
  {
    "type": "get",
    "url": "/image/types",
    "title": "获取所有的商品类型",
    "group": "image",
    "version": "0.0.0",
    "filename": "web/ImageController.java",
    "groupTitle": "image",
    "name": "GetImageTypes"
  },
  {
    "type": "post",
    "url": "/image/upload",
    "title": "",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "image",
            "description": "<p>要传入的文件</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "category",
            "description": "<p>类别</p>"
          }
        ]
      }
    },
    "group": "image",
    "version": "0.0.0",
    "filename": "web/ImageController.java",
    "groupTitle": "image",
    "name": "PostImageUpload"
  },
  {
    "type": "",
    "url": "/plot/image/:img/:refresh",
    "title": "获取画出的图片",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "img",
            "description": "<p>绘制图片的id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "refresh",
            "description": "<p>刷新的地址</p>"
          }
        ]
      }
    },
    "group": "plot",
    "version": "0.0.0",
    "filename": "web/ImagePlotController.java",
    "groupTitle": "plot",
    "name": "PlotImageImgRefresh"
  },
  {
    "type": "post",
    "url": "/plot/feature",
    "title": "绘制特征点图",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "image",
            "description": "<p>图像文件</p>"
          }
        ]
      }
    },
    "group": "plot",
    "version": "0.0.0",
    "filename": "web/ImagePlotController.java",
    "groupTitle": "plot",
    "name": "PostPlotFeature"
  },
  {
    "type": "post",
    "url": "/plot/match",
    "title": "绘制匹配图片",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "image1",
            "description": "<p>第一幅图像</p>"
          },
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "image2",
            "description": "<p>第二幅图像</p>"
          }
        ]
      }
    },
    "group": "plot",
    "version": "0.0.0",
    "filename": "web/ImagePlotController.java",
    "groupTitle": "plot",
    "name": "PostPlotMatch"
  },
  {
    "type": "",
    "url": "/save",
    "title": "将数据库的图像文件存在对应的位置",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "num",
            "description": "<p>每种类型存的个数</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pos",
            "description": "<p>图像存的位置</p>"
          }
        ]
      }
    },
    "group": "save",
    "version": "0.0.0",
    "filename": "web/SaveImageController.java",
    "groupTitle": "save",
    "name": "Save"
  },
  {
    "type": "post",
    "url": "/search/sift",
    "title": "通过sift算法检索图像",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "image",
            "description": "<p>图像</p>"
          }
        ]
      }
    },
    "group": "search",
    "version": "0.0.0",
    "filename": "web/SearchController.java",
    "groupTitle": "search",
    "name": "PostSearchSift"
  }
] });
