$(function() {
    $('div.bubble').fadeIn('slow').corner('5px round').bubble();
    $('div.bubble-top').fadeIn('slow').corner('5px round').bubble('top');

    // call again to show that nothing changes
    $('div.bubble').not(':has(.jquery-corner)').corner('5px round').bubble();
});