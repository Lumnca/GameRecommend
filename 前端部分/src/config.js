const HOST = 'http://47.106.254.86:8868';
axios
.post(HOST + "/vadd")
.then(res => {
  console.log(res.data);
})
.catch(() => {});