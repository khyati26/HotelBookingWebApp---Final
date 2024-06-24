 <!-- load JS files -->
        <script src="${pageContext.request.contextPath}/user/resources/js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="${pageContext.request.contextPath}/user/resources/js/popper.min.js"></script>                    <!-- https://popper.js.org/ -->
        <script src="${pageContext.request.contextPath}/user/resources/js/bootstrap.min.js"></script>                 <!-- https://getbootstrap.com/ -->
        <script src="${pageContext.request.contextPath}/user/resources/js/datepicker.min.js"></script>                <!-- https://github.com/qodesmith/datepicker -->
        <!-- <script src="${pageContext.request.contextPath}/user/resources/js/jquery.singlePageNav.min.js"></script> -->      <!-- Single Page Nav (https://github.com/ChrisWojcik/single-page-nav) -->
        <script src="${pageContext.request.contextPath}/user/resources/slick/slick.min.js"></script>                  <!-- http://kenwheeler.github.io/slick/ -->
        <script type="text/javascript">
                   function setCarousel() {
                
                if ($('.tm-article-carousel').hasClass('slick-initialized')) {
                    $('.tm-article-carousel').slick('destroy');
                } 

                if($(window).width() < 438){
                    // Slick carousel
                    $('.tm-article-carousel').slick({
                        infinite: false,
                        dots: true,
                        slidesToShow: 1,
                        slidesToScroll: 1
                    });
                }
                else {
                 $('.tm-article-carousel').slick({
                        infinite: false,
                        dots: true,
                        slidesToShow: 3,
                        slidesToScroll: 1
                    });   
                }
            }

            function setPageNav(){
                if($(window).width() > 991) {
                    $('#tm-top-bar').singlePageNav({
                        currentClass:'active',
                        offset: 79
                    });   
                }
                else {
                    $('#tm-top-bar').singlePageNav({
                        currentClass:'active',
                        offset: 65
                    });   
                }
            }

           
       
            $(document).ready(function(){

                $(window).on("scroll", function() {
                    if($(window).scrollTop() > 100) {
                        $(".tm-top-bar").addClass("active");
                    } else {
                        //remove the background property so it comes transparent again (defined in your css)
                       $(".tm-top-bar").removeClass("active");
                    }
                });      

               

                // Date Picker
                //const pickerCheckIn = datepicker('#inputCheckIn');
                //const pickerCheckOut = datepicker('#inputCheckOut');
                
                // Slick carousel
                setCarousel();
                setPageNav();

                $(window).resize(function() {
                  setCarousel();
                  setPageNav();
                });

                // Close navbar after clicked
                $('.nav-link').click(function(){
                    $('#mainNav').removeClass('show');
                });

                

                // Update the current year in copyright
                $('.tm-current-year').text(new Date().getFullYear());                           
            });

        </script>  

