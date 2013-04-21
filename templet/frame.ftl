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
        .right{
            background-color: #dd1144;
        }
        .wrong{
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

    </style>
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</head>
<div class="container" style="width: 100%;height: 90%">
<body>
    <#include "templet/head.html">  
    <div id="fill" style="height: 70px"></div>
    <div class="title"><h1>&nbsp;&nbsp;测试报告</h1></div>
        <div class="row-fluid">
            <div class="span1">
            </div>
            <div class="span11">
                <#include "templet/table.ftl">
            </div>
        </div>

    </div>
<#include "tmplet/foot.html">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>