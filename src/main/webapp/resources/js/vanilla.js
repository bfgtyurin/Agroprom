//jquery categories list
(function () {
  console.log('hello');
  $(window).ready(function () {
    loadCategoryTable();
  });

  var categoryTable = $('#category-table');

  function loadCategoryTable() {
    categoryTable.load('category/list.html', function () {
      fetchCategories();
    });
  }

  function fetchCategories() {
    $.ajax({
      method: 'GET',
      url: '/agroprom/category',
      dataType: 'json',
      success: function (response) {
        console.log('resived categories from /category :' + response);
        loadCategories(response);
      }
    });
  }

  function loadCategories(response) {
    var categoryTableBody = $('#category-table-body');
    var rowToAppend = '';
    for (var i = 0, len = response.length; i < len; i++) {
      var row = '<tr>';
      row += concatRow(response[i].id);
      row += concatRow(response[i].name);
      var parentName = response[i].parent;
      row += (parentName != null) ? concatRow(parentName.name) : concatRow('');
      row += '</tr>';
      rowToAppend += row;
    }

    categoryTableBody.append(rowToAppend);
  }

  function concatRow(data) {
    var begin = '<td>';
    var end = '</td>';
    return begin + data + end;
  }
})();