<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper row3">
	<main class="hoc container clear">
		<div class="content">
			<div style="height: 100px; width: 100%; padding-top: 1px; border-color: gray; border: solid 1px;">
				<div style="width: 20%; height: 30px; margin: 0 auto; margin-top: 2%; text-align: center; float:left; margin-left:10%;">
					<select style="float: left;height: 30px;" id="selectStock">
						<c:forEach var="userStockList" items="${userStockList }">
							<option value="${userStockList.stockCode }">${userStockList.stockName }</option>
						</c:forEach>
					</select>					
				</div>
				<div style="width: 20%; height: 30px; margin: 0 auto; margin-top: 2%; text-align: center; float:left;">
					<button style="margin-left:1%; float:left;" id="addNewData">추가</button>
					<button style="margin-left:1%; float:left;" id="modifyLastData">수정</button>
					<button style="margin-left:1%; float:left;" id="deleteLastData">삭제</button>
				</div>
				<div style="width:20%; float:left; margin-top:2%;">
					<label for="stockCode" style=" float: left;">종목코드</label>
					<input type="text" id="stockCode" style="float:left;">
				</div>
				<div style="width:20%; float:left; margin-top:2%;">
					<label for="stockName" style=" float: left;">종목명</label>
					<input type="text" id="stockName" style="float:left;">
				</div>
				<button id="addNewStock" style="float:left; margin-top:2%;">종목추가</button>
			</div>
			<div style="width: 25%; height: 500px; float: left; border-color: gray; border: solid 1px;" id="beforeData">
				<div id="beforeData" style="width: 100%; margin-top: 5%; text-align:center;">     
					<h3>마지막 데이터</h3>              
					<div class="stkDiv1"><label>날짜</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>현재가</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>매수수량</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적수량</label><input class="stockipt " type="text"></div>
					<div class="stkDiv1"><label>매수수수료</label><input class="stockipt " type="text"></div>
					<div class="stkDiv1"><label>투자액</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적투자액</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>매도수수료</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>세금</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적평가액</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>수익률(백분위)</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>수익률</label><input class="stockipt " type="text"></div>					  
				</div>
			</div>
			<div style="width: 25%; height: 500px; float: left; border-color: gray; border: solid 1px;" id="nowData">
				<div id="addData" style="width: 100%; margin-top: 5%; text-align:center;">
					<h3>등록할 데이터</h3>
					<div class="stkDiv1"><label>날짜</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>현재가</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>매수수량</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적수량</label><input class="stockipt " type="text"></div>
					<div class="stkDiv1"><label>매수수수료</label><input class="stockipt " type="text"></div>
					<div class="stkDiv1"><label>투자액</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적투자액</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>매도수수료</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>세금</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>누적평가액</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>수익률(백분위)</label><input class="stockipt " type="text"></div>  
					<div class="stkDiv1"><label>수익률</label><input class="stockipt " type="text"></div>
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