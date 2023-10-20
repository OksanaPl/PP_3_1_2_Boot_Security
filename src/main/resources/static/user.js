$(document).ready(async function () {
    await getTableWithUser();

});

const userFetchService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },

    findUser: async () => await fetch('api/rest/user'),
}

async function getTableWithUser() {
    let table = $('#tbody');
    table.empty();

    await userFetchService.findUser()
        .then(res => res.json())
        .then(user => {
            $('#nav_bankAccount').html(user.bankAccount);
            $('#nav_roles').html(user.roles.map(role => role.name).join(' '));
            let tableFilling = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>                                
                            <td>${user.bankAccount}</td>                                       
                            <td>
                                ${user.roles.map(role => role.name).join(' ')}
                            </td>                           
                        </tr>
                )`;
            table.append(tableFilling);
        })
}