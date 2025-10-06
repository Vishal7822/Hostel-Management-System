document.addEventListener('DOMContentLoaded', () => {

    // ---------- Student Form ----------
    const studentForm = document.getElementById('studentForm');
    const roomSelect = document.getElementById('roomSelect');
    const dueAmountInput = document.getElementById('dueAmount');
    const priceInput = document.getElementById('price');
    const paidInput = document.getElementById('paidAmount');
    const studentTable = document.getElementById('studentTable'); // if using table to show students

    // Auto-calculate due amount
    function calculateDue() {
        const price = parseFloat(priceInput?.value) || 0;
        const paid = parseFloat(paidInput?.value) || 0;
        if(dueAmountInput) dueAmountInput.value = price - paid;
    }
    priceInput?.addEventListener('input', calculateDue);
    paidInput?.addEventListener('input', calculateDue);

    // ---------- Load Available Rooms ----------
    function loadRooms() {
        if(!roomSelect) return;
        fetch('/rooms/available')
            .then(res => res.json())
            .then(data => {
                roomSelect.innerHTML = '<option value="">Select Room</option>';
                data.forEach(room => {
                    const option = document.createElement('option');
                    option.value = room.roomNumber; // must match backend JSON
                    option.textContent = `${room.roomNumber} (${room.roomType})`;
                    roomSelect.appendChild(option);
                });
            })
            .catch(err => console.error('Error loading rooms:', err));
    }
    loadRooms();

    // ---------- Submit Student Form ----------
    studentForm?.addEventListener('submit', function (e) {
        e.preventDefault();
        const studentData = {
            name: document.getElementById('name').value,
            startDate: document.getElementById('startDate').value,
            dueDate: document.getElementById('dueDate').value,
            price: parseFloat(document.getElementById('price').value),
            paidAmount: parseFloat(document.getElementById('paidAmount').value),
            dueAmount: parseFloat(document.getElementById('dueAmount').value),
            roomNumber: document.getElementById('roomSelect').value,
            classType: document.getElementById('classType').value
        };

        fetch('/students/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(studentData)
        })
        .then(res => {
            if(res.ok){
                alert('Student added successfully!');
                studentForm.reset();
                if(dueAmountInput) dueAmountInput.value = '';
                loadRooms(); // refresh room dropdown
                loadStudents(); // refresh student table if exists
            } else {
                alert('Error adding student');
            }
        })
        .catch(err => console.error('Error:', err));
    });

    // ---------- Load Students Table ----------
    function loadStudents() {
        if(!studentTable) return;
        fetch('/students/all')
            .then(res => res.json())
            .then(data => {
                studentTable.innerHTML = '';
                data.forEach(student => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.startDate}</td>
                        <td>${student.dueDate}</td>
                        <td>${student.price}</td>
                        <td>${student.paidAmount}</td>
                        <td>${student.dueAmount}</td>
                        <td>${student.roomNumber}</td>
                        <td>${student.classType}</td>
                        <td>
                            <button class="btn btn-sm btn-warning edit-student" data-id="${student.id}">Edit</button>
                            <button class="btn btn-sm btn-danger delete-student" data-id="${student.id}">Delete</button>
                        </td>
                    `;
                    studentTable.appendChild(tr);
                });

                // Edit & Delete functionality
                document.querySelectorAll('.delete-student').forEach(btn => {
                    btn.addEventListener('click', () => {
                        const id = btn.dataset.id;
                        if(confirm('Are you sure to delete this student?')){
                            fetch(`/students/delete/${id}`, { method: 'DELETE' })
                            .then(res => {
                                if(res.ok) loadStudents();
                                else alert('Error deleting student');
                            });
                        }
                    });
                });

                document.querySelectorAll('.edit-student').forEach(btn => {
                    btn.addEventListener('click', () => {
                        const id = btn.dataset.id;
                        // Redirect to edit page or populate form for editing
                        window.location.href = `/students/edit/${id}`;
                    });
                });
            })
            .catch(err => console.error('Error loading students:', err));
    }
    loadStudents();

    // ---------- Room Form ----------
    const roomForm = document.getElementById('roomForm');
    const roomTable = document.getElementById('roomTable');

    roomForm?.addEventListener('submit', function(e){
        e.preventDefault();
        const roomData = {
            roomNumber: document.getElementById('roomNumber').value,
            roomType: document.getElementById('roomType').value,
            isOccupied: false
        };
        fetch('/rooms/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(roomData)
        })
        .then(res => {
            if(res.ok){
                alert('Room added!');
                roomForm.reset();
                loadRooms();
                loadRoomsTable();
            } else alert('Error adding room');
        })
        .catch(err => console.error('Error:', err));
    });

    // ---------- Load Rooms Table ----------
    function loadRoomsTable() {
        if(!roomTable) return;
        fetch('/rooms/all')
            .then(res => res.json())
            .then(data => {
                roomTable.innerHTML = '';
                data.forEach(room => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
                        <td>${room.roomNumber}</td>
                        <td>${room.roomType}</td>
                        <td>${room.isOccupied ? 'Occupied' : 'Available'}</td>
                        <td>
                            <button class="btn btn-sm btn-warning edit-room" data-id="${room.roomNumber}">Edit</button>
                            <button class="btn btn-sm btn-danger delete-room" data-id="${room.roomNumber}">Delete</button>
                        </td>
                    `;
                    roomTable.appendChild(tr);
                });

                document.querySelectorAll('.delete-room').forEach(btn => {
                    btn.addEventListener('click', () => {
                        const roomNumber = btn.dataset.id;
                        if(confirm('Delete this room?')){
                            fetch(`/rooms/delete/${roomNumber}`, { method: 'DELETE' })
                            .then(res => {
                                if(res.ok) loadRoomsTable();
                                else alert('Error deleting room');
                            });
                        }
                    });
                });

                document.querySelectorAll('.edit-room').forEach(btn => {
                    btn.addEventListener('click', () => {
                        const roomNumber = btn.dataset.id;
                        window.location.href = `/rooms/edit/${roomNumber}`;
                    });
                });
            })
            .catch(err => console.error('Error loading rooms:', err));
    }
    loadRoomsTable();

});
