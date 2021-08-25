package com.vendixxx.monitor.registry;

import com.vendixxx.monitor.common.annotation.ServiceMeta;
import com.vendixxx.monitor.common.rpc.InstanceDetail;
import com.vendixxx.monitor.registry.model.HttpRemoteModel;
import com.vendixxx.monitor.registry.model.RemoteModel;

/**
 * registry 抽象类
 *
 * @author liuzheng
 * @date 2021-01-18
 * @since 2021
 */
public abstract class AbstractHttpRegistry implements Registry {

    private InstanceDetail build(RemoteModel remoteModel, ServiceMeta serviceMeta) {
        if(!remoteModel.getClass().isAssignableFrom(HttpRemoteModel.class)){
            throw new IllegalStateException("");
        }
        HttpRemoteModel httpRemoteModel = (HttpRemoteModel)remoteModel;
        boolean degrade = serviceMeta == null ? false : serviceMeta.degrade();
        InstanceDetail.InstanceDetailBuilder builder = new InstanceDetail.InstanceDetailBuilder();
        builder.address(httpRemoteModel.getAddress())
                .env(httpRemoteModel.getEnv())
                .methodName(httpRemoteModel.getMethodName())
                .controllerRequestMapping(httpRemoteModel.getControllerMapping())
                .methodReuqestMapping(httpRemoteModel.getMethodMapping())
                .degrade(degrade);

        InstanceDetail instanceDetail = builder.build();
        return instanceDetail;
    }

    @Override
    public void register(RemoteModel remoteModel, ServiceMeta serviceMeta) {
        InstanceDetail instanceDetail = build(remoteModel, serviceMeta);
        doRegister(remoteModel, serviceMeta, instanceDetail);
    }

    @Override
    public void unregister(RemoteModel remoteModel, ServiceMeta serviceMeta) {
        InstanceDetail instanceDetail = build(remoteModel, serviceMeta);
        doUnregister(remoteModel, serviceMeta, instanceDetail);
    }

    /**
     * 抽象方法 上线服务
     *
     * @param remoteModel
     * @param serviceMeta
     * @param instanceDetail
     */
    protected abstract void doRegister(RemoteModel remoteModel, ServiceMeta serviceMeta, InstanceDetail instanceDetail);

    /**
     * 抽象方法 下线服务
     *
     * @param remoteModel
     * @param serviceMeta
     * @param instanceDetail
     */
    protected abstract void doUnregister(RemoteModel remoteModel, ServiceMeta serviceMeta, InstanceDetail instanceDetail);
}
