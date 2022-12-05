function loadComments () {

    this.source = null;

    this.start = function () {

        var commentTable = document.getElementById("comments");

        this.source = new EventSource("/api");

        this.source.addEventListener("message", function (event) {

            // These events are JSON, so parsing and DOM fiddling are needed
            var comment = JSON.parse(event.data);

            var row = commentTable.getElementsByTagName("tbody")[0].insertRow(0);
            var cell0 = row.insertCell(0);
            var cell1 = row.insertCell(1);
            var cell2 = row.insertCell(2);
            var cell3 = row.insertCell(3);
            var cell4 = row.insertCell(4);


            // cell0.className = "author-style";
            cell0.innerHTML = comment.id;

            // cell1.className = "text";
            cell1.innerHTML = comment.ipClient;

            // cell2.className = "text";
            cell2.innerHTML = comment.ipServer;

            if(comment.status == 'Thành công') cell3.className = "badge badge-success";
            else cell3.className = "badge badge-danger";
            cell3.innerHTML = comment.status;

            // cell4.className = "date";
            cell4.innerHTML = comment.date;

        });

        this.source.onerror = function () {
            this.close();
        };

    };

    this.stop = function() {
        this.source.close();
    }
}

comment = new loadComments();



/*
 * Register callbacks for starting and stopping the SSE controller.
 */
window.onload = function() {
    comment.start();
};
window.onbeforeunload = function() {
    comment.stop();
}

// setTimeout(function(){
//     window.location.reload();
// }, 3000);
