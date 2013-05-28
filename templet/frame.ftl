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

    </style>
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
</head>
<body>
	<div class="container" style="width: 100%;height: 90%">
    <#include "head.html">  
    <div id="fill" style="height: 70px"></div>
        <div class="row-fluid">
            <div class="span12">
            	<div class="result">
                <h2>运行环境</h2>
                <table class="table table-bordered" style="width: 700px; margin:auto">
                    <thead>
                    <tr style="background-color: #00b3ee">
                        <th>运行消耗时间</th>
                        <th>运行结束时间</th>
                        <th>运行用例的总数</th>
                        <th>用例执行失败数</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr style="background-color: #ffffff">
                        <td>${res.runTime}</td>
                        <td>${res.time}</td>
                        <td>${res.runCount}</td>
                        <td>${res.failureCount}</td>
                    </tr>
                    </tbody>
                </table>
            	</div>
            <#include "table.ftl">
            </div>
        </div>
    </div>
<#include "foot.html">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>