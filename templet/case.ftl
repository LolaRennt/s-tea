<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>JUnit测试报告</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <style type="text/css">
        html, 
        body { 
            height: 100%;
            width: 100%;
        } 
        body{ 
            background: url("bg.jpg");
        }
        #footer {
            height: 60px;
        }
        #footer {
            background-color: #f5f5f5;
        }

        @media (max-width: 767px) {
            #footer {
                margin-left: -20px;
                margin-right: -20px;
                padding-left: 20px;
                padding-right: 20px;
            }
        }
        .container {
            width: auto;
            max-width: 1100px;
        }
        .container .credit {
            margin: 20px 0;
        }
        .table td{
            max-width: 300px;
            word-break:break-all;
            min-width: 70px;
            vertical-align: middle;
            text-align: center;
        }
        .table th{
            vertical-align: middle;
            text-align: center;
        }
        .wrong{
            background-color: #dd1144;
        }
        .right{
            background-color: #387038;
        }
        .table a{
            color:#a9dba9;
            text-decoration:none;
            font-weight:bold;
            font-size: 16px;
        }
        .table a:hover{
            color: #7a43b6;
            text-decoration:none;
            font-weight:bold;
        }
		 .info{
            background: #ffffff;
        }
    </style>
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</head>
<body>
<div class="container" style="width: 100%;height: 90%">
    <#include "head.html">  
    <div id="fill" style="height: 70px"></div>
        <div class="row-fluid">
            <div class="span12">
            <#include "casetable.ftl">
       		</div>
    	</div>
<div class="blank">
	<h2>&nbsp;&nbsp;</h2>
</div>
 </div>
<#include "foot.html">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>