(function(jQuery) { 
  jQuery.fn.bubble = function(type, options) {
    options = jQuery.extend({
      height: 14,
      margin: 60
    }, options);
    var isTop       = /top/.test((type||'').toLowerCase());
    var marginTypeY = isTop ? 'margin-top' : 'margin-bottom';
    var sign        = isTop ? 1 : -1;
    
    return this.each(function(index) {
      var offset = isTop ? options.margin : jQuery(this).attr('clientWidth') - options.margin;
      var base   = isTop ? 0 : jQuery(this).attr('clientHeight');
      var margin = isTop ? options.height : (parseInt(jQuery(this).css(marginTypeY))||0) + options.height;

      // create a prototype div
      var strip = document.createElement('div');
      strip.style.overflow = 'hidden';
      strip.style.height = '1px';
      strip.style.backgroundColor = jQuery(this).css('background-color');
      strip.style.position = 'absolute';
      
      // apply a margin, so that the next/previous element does not hide the bubble triangle
      jQuery(this).css(marginTypeY, margin);
        
      // add the triangle strips
      for (var i = options.height; i > 0; i--) {
        var c = strip.cloneNode(false);
        var width = 2*i - 1;
        c.style.width = width + 'px';
        c.style.marginLeft = (offset - Math.round(width / 2)) + 'px';
        c.style.top = (base - sign*(options.height - i)) + 'px';
        jQuery(this).append(c);
      }
    });
  }
})(jQuery);