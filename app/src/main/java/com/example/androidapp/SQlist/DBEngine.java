package com.example.androidapp.SQlist;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class DBEngine{

//    接收 传入的 AgencyDao 对象
    private AgencyDao agencyDao;

//    接收 获取的所有数据 实现单例
    private static List<Agency> allAgencies;

    //    实现 构造方法
    public DBEngine(Context context){
        AgencyDatabase agencyDatabase = AgencyDatabase.getInstance(context);
        agencyDao = agencyDatabase.getAgencyDao();
    };

//*********************************************************************************************

//    插入 操作
    public void insertAgencies(Agency... agencies){
//        开启 InsertAsyncTask 线程
        new InsertAsyncTask(agencyDao).execute(agencies);
    }

//    更新 操作
    public void updateAgencies(Agency... agencies){
        new UpdateAsyncTask(agencyDao).execute(agencies);
    }

//    删除 操作
    public void  deleteAgencies(Agency... agencies){
        new DeleteAsyncTask(agencyDao).execute(agencies);
    }

//    获取 操作
    public void getAllAgencies(Agency... agencies){
        new GetAllAgencies(agencyDao).execute();
    }

//*********************************************************************************************

//    实现 插入功能
    private static class InsertAsyncTask extends AsyncTask<Agency,Void,Void> {

//        获取 AgencyDao 对象
        private AgencyDao dao;

//        实现 构造方法
        public InsertAsyncTask(AgencyDao agencyDao) {
            dao = agencyDao;
        }

//        实现 AgencyDao 操作
        @Override
        protected Void doInBackground(Agency... agencies) {
            dao.insertAgencies(agencies);
            return null;
        }
    }
//    实现 更新功能
    private static class UpdateAsyncTask extends AsyncTask<Agency,Void,Void>{

        private AgencyDao dao;

        public UpdateAsyncTask(AgencyDao agencyDao) {
            dao = agencyDao;
        }

        @Override
        protected Void doInBackground(Agency... agencies) {
            dao.updateAgencies(agencies);
            return null;
        }
    }

//    实现 删除功能
    private static class DeleteAsyncTask extends AsyncTask<Agency,Void,Void> {
        private AgencyDao dao;

        public DeleteAsyncTask(AgencyDao agencyDao) {
            dao = agencyDao;
        }

        @Override
        protected Void doInBackground(Agency... agencies) {
            dao.deleteAgencies(agencies);
            return null;
        }
    }

//    实现 获取所有数据 功能
    private static class GetAllAgencies extends AsyncTask<Void,Void,List<Agency>> {

        private AgencyDao dao;

        public GetAllAgencies(AgencyDao agencyDao) {
            dao = agencyDao;
        }


//        获取数据
        @Override
        protected List<Agency> doInBackground(Void... voids) {
                allAgencies = dao.getAllAgency();
            return allAgencies;
        }

//        返回 数据
        @Override
        protected void onPostExecute(List<Agency> agencies) {
            super.onPostExecute(agencies);

            allAgencies =agencies;
        }
}

//*********************************************************************************************


    public List<Agency> getAllAgenciesData() {
        return allAgencies;
    }

    public void setAllAgenciesData(List<Agency> allAgencies) {
        DBEngine.allAgencies = allAgencies;
    }
}

