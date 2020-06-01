<template>
  <div>
    <el-tree
      :data="menus"
      :props="defaultProps"
      @node-click="handleNodeClick"
      show-checkbox="true"
      :expand-on-click-node="false"
      :default-expanded-keys="expandKey"
      node-key="catId"
      draggable
      :allow-drop="allowDrop"
      
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if="node.level<=2" type="text" size="mini" @click="() => append(data)">Append</el-button>
          <el-button type="text" size="mini" @click="() => fix(data)">Fix</el-button>
          <el-button
            v-if="node.childNodes.length==0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >Delete</el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible">
      <el-form :model="category">
        <el-form-item label="分类名称" :label-width="formLabelWidth">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类图标" :label-width="formLabelWidth">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位" :label-width="formLabelWidth">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog.visible = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      category: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        sort: 0,
        showStatus: 1,
        icon: null,
        productUnit: null,
        catId:null
      },
      menus: [],
      defaultProps: {
        children: "children",
        label: "name"
      },
      expandKey: [],

      dialog: {
        mode: "add",
        title: "test",
        visible: false
      },
      maxLevel:0
    };
  },
  activated() {
    this.getDataList();
  },
  methods: {
    // handleNodeClick(data) {
    //   console.log(data);
    // },
    submit(){
      this.dialog.mode=="add"?this.commit():this.edit()
    },
    fix(data) {
      let [mode, title, visible] = ["edit", data.name, true];
      this.dialog = { mode, title, visible };
         this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: "get" 
      }).then(({ data }) => {
        if (data && data.code === 0) {
           this.category.name=data.data.name
           this.category.icon=data.data.icon
           this.category.productUnit=data.data.productUnit
           this.category.catId=data.data.catId
           this.category.catLevel=data.data.catLevel
           this.category.parentCid=data.data.parentCid
            
        } else {
          this.$message.error(data.msg);
        }
      });

    },
    edit(){
       console.log("editing")
       const {name,icon,productUnit,catId} =this.category
       this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        data: this.$http.adornData({name,icon,productUnit,catId} , false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "操作成功",
            type: "success",
            duration: 1500,
            onClose: () => {
              this.getDataList();
              this.dialog.visible = false;

              this.expandKey = [this.category.parentCid];
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    commit() {
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "操作成功",
            type: "success",
            duration: 1500,
            onClose: () => {
              this.getDataList();
              this.dialog.visible = false;

              this.expandKey = [this.category.parentCid];
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/product/category/list"),
        method: "get"
      }).then(({ data }) => {
        console.info(data);
        this.menus = data.data;
      });
    },
    append(data) {
      console.log(data);
      let [mode, title, visible] = ["add", "添加分类", true];
      this.dialog = { mode, title, visible };
      this.category.parentCid = data.catId;
      this.category.catLevel = data.catLevel + 1;
      this.category.name=""
      this.category.icon=null
      this.category.productUnit=null
      
    },
    remove(a, b) {
      console.log("remove", a, b);
      let catIds = [b.catId];
      this.expandKey = [a.parent.data.catId];
      this.$confirm(`确定对[id=${b.catId}]进 删除 操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/product/category/delete"),
            method: "post",
            data: this.$http.adornData(catIds, false)
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.getDataList();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        })
        .catch(() => {});
    },
      allowDrop(draggingNode, dropNode, type) {
        console.log("allowDrop",draggingNode, dropNode, type)
        this.countMaxLevel(draggingNode.data)
        let deep=this.maxLevel -draggingNode.level+1
        console.log("maxlevel",this.maxLevel)
        console.log("dragginNode.leve",draggingNode.level)
        console.log("deep",deep)
        
        if(type=="inner"){
          return deep+dropNode.level<=3
        }else{
          return deep+dropNode.parent.level<=3
        }
      //  if(draggingNode.level+dropNode.level>3){
      //    return false
      //  } else{
      //    return true
      //  } 
      },
      countMaxLevel(node){
        if(node.children!=null&&node.children.length>0){
          for(let i=0;i<node.children.length;i++)
          {

                if(node.children[i].catLevel>this.maxLevel)
                   this.maxLevel=node.children[i].catLevel
                   this.countMaxLevel(node.children[i])  
          }
           
        }
      }
  }
};
</script>

<style    >
</style>