const HOST = 'http://127.0.0.1:8868';
axios
.post(HOST + "/vadd")
.then(res => {
  console.log(res.data);
})
.catch(() => {});