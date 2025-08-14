async function uploadToServer(formObj) {
    console.log("upload to server...")

    const response = await axios({
        method: 'post',
        url: '/upload',
        data: formObj,
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });

    return response.data
}

async function removeFileToServer(link) {
    const response = await axios.delete(`/remove/link`)

    return response.data
}