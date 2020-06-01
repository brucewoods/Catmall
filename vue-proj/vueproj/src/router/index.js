import Vue from 'vue';
import Router from 'vue-router';
import HelloWorld from '@/components/HelloWorld';
import Hey from '@/components/Hey';
import Users from '@/components/Users'
Vue.use(Router);

export default new Router({
    routes: [{
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld,
        },

        {
            path: '/hey',
            name: 'Hey',
            component: Hey,
        },
        {
            path: '/ulist',
            name: 'Users',
            component: Users,
        },

    ],
});