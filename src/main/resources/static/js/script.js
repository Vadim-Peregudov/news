$(document).ready(function () {
    // Default number of items to display per page in the news list
    const defaultPageSize = 10;
    // Default initial page number to display in the news list
    const defaultCurrentPage = 1;
    // Function to create pagination buttons
    function createButtonPagination(currentPage, totalPages) {
        let pagination = $('.pagination');// Selecting the pagination element

        pagination.empty();// Clearing the existing content of pagination

        for (let i = 1; i <= totalPages; i++) {
            let button = $('<button>', { // Creating a button element
                class: 'page-link btn btn-link', // Adding CSS classes to the button
                value: i, // Setting the value of the button
                text: i, // Setting the text of the button
            });
            let li = $('<li>', { // Creating a list item element
                class: 'page-item' // Adding a CSS class to the list item
            });
            if (currentPage == i) { // Checking if the current page matches the iteration value
                li.addClass('active'); // Adding the 'active' class to the list item
            }
            li.append(button); // Appending the button to the list item
            button.on('click', function () { // Adding a click event listener to the button
                $(".page-item.active").removeClass("active"); // Removing the 'active' class from the previously active item

                $(this).closest("li").addClass("active"); // Adding the 'active' class to the clicked item

                updateNewsList($("#newsPerPage").val(), $(this).val()); // Calling the updateNewsList function with the selected values
            });
            pagination.append(li); // Appending the list item to the pagination element
        }
    }
    // Function to update the news list
    function updateNewsList(pageSize = defaultPageSize, currentPage = defaultCurrentPage) {
        $.ajax({
            type: 'POST',
            url: '/home/api/news/get-html-pagination',// The URL for the AJAX request to fetch news data
            data: {
                pageSize: pageSize, // The number of news items per page
                currentPage: currentPage, // The current page number
            },
            success: function (data) {
                $('#newsList').html(data.newsHtml); // Update the news list with the retrieved HTML content
                createButtonPagination(data.currentPage, data.totalPages); // Create pagination buttons
            },
            error: function (xhr, status, error) {
                alert('error:' + error);
            }
        });
    }

    // Initialize the news list on page load
    updateNewsList();

    // Event handler for form change
    $('form').change(function () {
        let selectedValue = $('#newsPerPage').val(); // Get the selected value from the newsPerPage element
        updateNewsList(selectedValue); // Call the updateNewsList function with the selected value
    });


});




