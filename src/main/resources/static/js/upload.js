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

async function removeFileToServer(fullFileName) {
    console.log("removeFileToServer() 호출됨:", fullFileName)
    const response = await axios.delete(`/remove/${fullFileName}`)

    return response.data
}