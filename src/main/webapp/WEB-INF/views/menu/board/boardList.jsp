<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="wrapper row3">
  <main class="hoc container clear"> 
    <div class="content"> 
      <div id="gallery" class="brdList">
        <figure>
          <header class="heading">BOARD LIST</header>
          <div class="loading" id="loadingDiv">
          	<span class="spnTxt">조회중 입니다</span>
          </div>
          <ul style="display:none" class="nospace clear" id="boards">
          </ul>
          <figcaption><a style="float:right; color : blueviolet; font-size : 25px;" href="/board/registBoard.do">글쓰기</a></figcaption>
        </figure>
      </div>
      <nav class="pagination_2">
        <ul id="pageNavi">          
        </ul>
      </nav>
    </div>
    <div class="clear"></div>
  </main>
</div>