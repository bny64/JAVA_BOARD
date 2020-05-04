<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="nowStockName" value=""></input>
<input type="hidden" id="nowStockCode" value=""></input>
<input type="hidden" id="nowStockKey" value=""></input>
<div class="wrapper row3">
	<main class="hoc container clear">
		<div class="content">			
			<div style="height: 70px; width: 100%; padding-top: 1px; border-color: gray; border: solid 1px;">
				<div style="width: 20%; height: 30px; margin: 0 auto; margin-top: 1.5%; text-align: center; float:left; margin-left:5%;">
					<label style="margin-top:2%; margin-right:2%; float:left;">종목선택</label>					
					<select style="float: left;height: 30px; margin-left:3%; width:120px;" id="selectStock">						
					</select>					
				</div>				
				<div style="width:20%; float:left; margin-top:2%;">
					<label for="stockCode" style=" float: left; margin-right:5%;">종목코드</label>
					<input type="text" id="stockCode" style="float:left; margin-top : -0.5%; text-align:center; width:100px;">
				</div>
				<div style="width:20%; float:left; margin-top:2%;">
					<label for="stockName" style=" float: left; margin-right:5%;">종목명</label>
					<input type="text" id="stockName" style="float:left; margin-top : -0.5%; text-align:center; width:100px;">
				</div>
				<div style="width:20%; float:left; margin-top:2%;">
					<label for="stockName" style=" float: left; margin-right:5%;">종목별칭</label>
					<input type="text" id="stockNickName" style="float:left; margin-top : -0.5%; text-align:center; width:100px;">
				</div>
				<button id="addNewStock" style="float:left; margin-top:2%;">종목추가</button>
				<button id="deleteStock" style="display:none; float:left; margin-top:2%;">종목삭제</button>
			</div>
			<div style="height: 50px; width: 100%; padding-top: 1px; border-color: gray; border-left:black solid 1px; border-right:black solid 1px;">
				<button style="float : right; margin-right:2%; margin-top:1%;">데이터 추가</button>
			</div>
			<div style="height: 450px; width: 100%; padding-top: 1px; border-top : none !important; border-color: gray; border: solid 1px;" id="stockGrid">
			</div>
			<div style="width: 25%; height: 500px; float: left; border-color: gray; border: solid 1px;" id="beforeData">
				<div id="beforeData" style="width: 100%; margin-top: 5%; text-align:center;">     
					<h3>마지막 데이터</h3>              
					<div class="stkDiv1"><label>날짜</label><input data-name="stockDate" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>현재가</label><input data-name="nowPrc" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>매수수량</label><input data-name="byMnt" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적수량</label><input data-name="accMnt" class="stockipt " type="text"></div>
					<div class="stkDiv1"><label>매수수수료</label><input data-name="buySrvfee" class="stockipt " type="text"></div>
					<div class="stkDiv1"><label>투자액</label><input data-name="ivstPrc" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적투자액</label><input data-name="accIvstPrc" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>매도수수료</label><input data-name="sellSrvfee" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>세금</label><input data-name="taxFee" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적평가액</label><input data-name="accEstPrc" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>수익률(백분위)</label><input data-name="ernRatePer" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>수익률</label><input data-name="ernRate" class="stockipt " type="text"></div>				  
				</div>
			</div>
			<div style="width: 25%; height: 500px; float: left; border-color: gray; border: solid 1px;" id="nowData">
				<div id="addData" style="width: 100%; margin-top: 5%; text-align:center;">
					<h3>등록할 데이터</h3>
					<div class="stkDiv1"><label>날짜</label><input data-name="stockDate" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>현재가</label><input data-name="nowPrc" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>매수수량</label><input data-name="byMnt" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적수량</label><input data-name="accMnt" class="stockipt " type="text"></div>
					<div class="stkDiv1"><label>매수수수료</label><input data-name="buySrvfee" class="stockipt " type="text"></div>
					<div class="stkDiv1"><label>투자액</label><input data-name="ivstPrc" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적투자액</label><input data-name="accIvstPrc" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>매도수수료</label><input data-name="sellSrvfee" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>세금</label><input data-name="taxFee" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적평가액</label><input data-name="accEstPrc" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>수익률(백분위)</label><input data-name="ernRatePer" class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>수익률</label><input data-name="ernRate" class="stockipt " type="text"></div>
					<button id="addBtn" style="margin: 0 auto; margin-top: 3%;">추가</button>
				</div>
			</div>
			<div style="width: 50%; height: 500px; float: right; border-color: gray; border: solid 1px;">
				<div id="stockGrid" style="height:90%"></div>
				<div style="height:10%; background-color: gray; padding-top:2%;">
					<button id="deleteStock" style="margin-left:43%;">종목 삭제</button>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</main>
</div>