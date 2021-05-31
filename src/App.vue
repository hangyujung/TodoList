<template>
  <div id="app" class="container">
    <h1 class="text-center">Todo App</h1>
    <input
        v-model="todoText"
        type="text"
        class="w-100 p-2"
        placeholder="Type todo"
        @keyup.enter="addTodo"
    >
    <hr>
    <Todo
        v-for="todo in todos"
        :key="todo.id"
        :todo="todo"
        @toggle-checkbox="toggleCheckbox"
        @click-delete="deleteTodo"
    />
  </div>
</template>

<script>
import Todo from '@/components/Todo.vue';


export default {
  components: {
    Todo
  },



  data(){
    return {
      todoText: '',
      todos: [],
    }
  },

  created (){

    this.axios.get('http://localhost:8081/api/todos').then((size) => {
      console.log(size.data)
      // for (var i = 1; i <= dbSize; i++) {
      //   this.axios.get('/todo/findOne/' + i).then((contents) => {
        this.todos=size.data

      //this.todos.push(size.data)
      //   })
      // }deleteTodo
    })
  },
  methods: {

    deleteTodo(id) {
      // const index = this.todos.findIndex(todo => {
      //   return todo.id === id;
      // this.todos.splice(index, 1);
      // });
      this.todos = this.todos.filter(todo => todo.id !== id);
      // const data={
      //   headers:{'Content-Type':'application/json'},
      //   method:'delete'
      // }
      this.axios.delete('http://localhost:8081/api/todos/'+id).then(res=>{
        console.log(res.data)
        console.log(id)
      })
    },

    addTodo(e) {
      this.todos.push({

        content: e.target.value,
        checked: false
      });
      // const data = {
      //   body: JSON.stringify({"content" : e.target.value}),
      //   headers: {'Content-Type': 'application/json'},
      // }
      this.axios.post('http://localhost:8081/api/todos',{
        content : e.target.value,
      }).then(res=>{
        console.log(res)
      })

      this.todoText = '';
    },

    toggleCheckbox({id, checked}) {
      const index = this.todos.findIndex(todo => {
        return todo.id === id;
      });
      this.todos[index].checked = checked;
    }
  }
}
</script>