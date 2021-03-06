<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
${hello }
<div class="wrapper row3">
  <main class="hoc container clear"> 
    <!-- main body -->
    <!-- ################################################################################################ -->
    <div class="sectiontitle">
      <h6 class="heading">게시판 목록</h6>
      <p>다른 사용자들과 게시판으로 정보를 공유하세요</p>
    </div>
    <div class="loading" id="loadingDiv">
       	<span class="spnTxt">조회중 입니다</span>
    </div>
    <ul class="nospace group overview" id="indexBoards">
      <!-- <li class="one_third">
        <figure><a href="#"><img src="/images/main/demo/348x261.png" alt=""></a>
          <figcaption>
            <h6 class="heading">Dapibus pede tristique</h6>
            <p>Congue quam erat et dui morbi at sapien non enim blandit.</p>
          </figcaption>
        </figure>
      </li>
      <li class="one_third">
        <figure><a href="#"><img src="/images/main/demo/348x261.png" alt=""></a>
          <figcaption>
            <h6 class="heading">Interdum morbi posuere</h6>
            <p>Etiam consequat arcu accumsan commodo luctus nibh fringilla.</p>
          </figcaption>
        </figure>
      </li>
      <li class="one_third">
        <figure><a href="#"><img src="/images/main/demo/348x261.png" alt=""></a>
          <figcaption>
            <h6 class="heading">Augue non vehicula</h6>
            <p>Erat neque et tortor ut molestie ultricies quam aliquam.</p>
          </figcaption>
        </figure>
      </li>
      <li class="one_third">
        <figure><a href="#"><img src="/images/main/demo/348x261.png" alt=""></a>
          <figcaption>
            <h6 class="heading">Erat volutpat integer</h6>
            <p>Posuere vulputate leo nullam eu tellus phasellus aliquam.</p>
          </figcaption>
        </figure>
      </li>
      <li class="one_third">
        <figure><a href="#"><img src="/images/main/demo/348x261.png" alt=""></a>
          <figcaption>
            <h6 class="heading">Tellus ut libero etiam</h6>
            <p>Ut metus quisque pretium nunc fermentum volutpat velit sed.</p>
          </figcaption>
        </figure>
      </li>
      <li class="one_third">
        <figure><a href="#"><img src="/images/main/demo/348x261.png" alt=""></a>
          <figcaption>
            <h6 class="heading">Dictum bibendum velit</h6>
            <p>Nulla iaculis pellentesque nunc felis tristique vel ultrices.</p>
          </figcaption>
        </figure>
      </li> -->
    </ul>
    <!-- ################################################################################################ -->
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
<!-- <div class="wrapper row2">
  <section class="hoc container clear"> 
    ################################################################################################
    <div class="sectiontitle">
      <h6 class="heading">Vitae congue at urna suspendisse</h6>
      <p>Vestibulum nisi ut lectus proin lectus ante fermentum sed</p>
    </div>
    <div class="group center">
      <article class="one_third first"><a class="ringcon btmspace-50" href="#"><i class="fas fa-eraser"></i></a>
        <h6 class="heading">Bibendum rutrum congue</h6>
        <p>Tortor proin sagittis mauris ac odio morbi ut massa donec suscipit eros ut justo etiam non orci nullam at tortor maecenas eu.</p>
      </article>
      <article class="one_third"><a class="ringcon btmspace-50" href="#"><i class="fas fa-dolly-flatbed"></i></a>
        <h6 class="heading">Neque lacinia ullamcorper</h6>
        <p>Posuere etiam in arcu nam sodales euismod tellus quisque nunc mauris vehicula in ultrices tempor imperdiet vitae sapien morbi.</p>
      </article>
      <article class="one_third"><a class="ringcon btmspace-50" href="#"><i class="fas fa-text-width"></i></a>
        <h6 class="heading">Accumsan dignissim libero</h6>
        <p>Suspendisse potenti vestibulum mattis suspendisse potenti praesent nec ligula ut non ante sit amet est luctus dictum duis magna.</p>
      </article>
    </div>
    ################################################################################################
  </section>
</div>
<div class="wrapper gradient">
  <section id="testimonials" class="hoc container clear"> 
    ################################################################################################
    <div class="sectiontitle">
      <h6 class="heading">Mauris tincidunt eu tincidunt</h6>
      <p>In egestas vitae turpis sed velit urna sollicitudin ut euismod</p>
    </div>
    <article class="one_half first"><img src="/images/main/demo/100x100.png" alt="">
      <blockquote>Nec hendrerit vel velit ut sodales feugiat nulla proin porttitor lacus eget mi quisque in eros ut porttitor dapibus enim duis mollis accumsan purus etiam quis lorem hendrerit.</blockquote>
      <h6 class="heading">G. Name</h6>
      <em>Neque convallis pretium</em></article>
    <article class="one_half"><img src="/images/main/demo/100x100.png" alt="">
      <blockquote>Vestibulum volutpat ut consequat tincidunt tortor nulla enim morbi id ante in felis iaculis faucibus duis nibh diam tristique scelerisque viverra at vulputate quis lacus donec.</blockquote>
      <h6 class="heading">H. Name</h6>
      <em>Vestibulum odio vivamus</em></article>
    ################################################################################################
  </section>
</div>
<div class="wrapper row2">
  <section id="latest" class="hoc container clear"> 
    ################################################################################################
    <div class="sectiontitle">
      <h6 class="heading">Ligula mi fringilla vel hendrerit</h6>
      <p>Et malesuada vitae risus in a enim in metus ultrices tristique</p>
    </div>
    <ul class="nospace group">
      <li class="one_third first">
        <article>
          <figure><a href="#"><img src="/images/main/demo/348x261.png" alt=""></a>
            <figcaption>
              <time datetime="2045-04-06T08:15+00:00"><strong>06</strong> <em>Apr</em></time>
            </figcaption>
          </figure>
          <div class="excerpt">
            <h6 class="heading">Integer aliquet dignissim tellus</h6>
            <ul class="nospace meta">
              <li><i class="fas fa-user"></i> <a href="#">Admin</a></li>
              <li><i class="fas fa-tags"></i> <a href="#">Tag 1</a>, <a href="#">Tag 2</a></li>
            </ul>
            <p>Vestibulum consequat praesent bibendum vehicula mi sed fermentum erat sit amet imperdiet dictum enim lectus [<a href="#">&hellip;</a>]</p>
            <footer><a class="btn" href="#">Read More</a></footer>
          </div>
        </article>
      </li>
      <li class="one_third">
        <article>
          <figure><a href="#"><img src="/images/main/demo/348x261.png" alt=""></a>
            <figcaption>
              <time datetime="2045-04-05T08:15+00:00"><strong>05</strong> <em>Apr</em></time>
            </figcaption>
          </figure>
          <div class="excerpt">
            <h6 class="heading">Tortor tempus metus neque</h6>
            <ul class="nospace meta">
              <li><i class="fas fa-user"></i> <a href="#">Admin</a></li>
              <li><i class="fas fa-tags"></i> <a href="#">Tag 1</a>, <a href="#">Tag 2</a></li>
            </ul>
            <p>Vel elit integer in orci vitae lacus ultricies mattis suspendisse congue sapien vel massa posuere lacinia [<a href="#">&hellip;</a>]</p>
            <footer><a class="btn" href="#">Read More</a></footer>
          </div>
        </article>
      </li>
      <li class="one_third">
        <article>
          <figure><a href="#"><img src="/images/main/demo/348x261.png" alt=""></a>
            <figcaption>
              <time datetime="2045-04-04T08:15+00:00"><strong>04</strong> <em>Apr</em></time>
            </figcaption>
          </figure>
          <div class="excerpt">
            <h6 class="heading">Phasellus a ipsum eget odio</h6>
            <ul class="nospace meta">
              <li><i class="fas fa-user"></i> <a href="#">Admin</a></li>
              <li><i class="fas fa-tags"></i> <a href="#">Tag 1</a>, <a href="#">Tag 2</a></li>
            </ul>
            <p>Fringilla tincidunt proin velit aliquam erat volutpat etiam elementum eros ut ante fusce a lacus ac neque [<a href="#">&hellip;</a>]</p>
            <footer><a class="btn" href="#">Read More</a></footer>
          </div>
        </article>
      </li>
    </ul>
    ################################################################################################
  </section>
</div> -->