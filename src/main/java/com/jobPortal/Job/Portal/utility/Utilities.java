package com.jobPortal.Job.Portal.utility;

import com.jobPortal.Job.Portal.entity.Sequence;
import com.jobPortal.Job.Portal.exception.JobPortalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;


@Component
public class Utilities {
    //springboot jab run hota hai tab it scans Components and autowires all
    private static MongoOperations mongoOperations;
//    static mei directly autowired nhi lagta( sealed injection(Atutowired) nut yha setter injection lagega in case of static in SB )
    // setter injection

    @Autowired
    public void setMongoOperations(MongoOperations mongoOperations){
        Utilities.mongoOperations=mongoOperations;
    }
    public static Long getNextSequence(String key) throws JobPortalException{
        Query query= new Query(Criteria.where("_id").is(key));
        Update update = new Update();
        update.inc("seq",1);//seq ki field mei 1 se increment karna hai
        FindAndModifyOptions options =  new FindAndModifyOptions();
        options.returnNew(true);// new updated value ko return karna hai
        Sequence seq = mongoOperations.findAndModify(query,update,options,Sequence.class);
        if(seq==null){
            throw new JobPortalException("unable t o get Sequence id for Key :" + key);
        }
        return seq.getSeq();
    }

    public static String generateOTP(){
        StringBuilder otp= new StringBuilder();
        SecureRandom random= new SecureRandom();
        for(int i=0;i<6;i++){
            otp.append(random.nextInt(10));
        }
        return otp.toString();

    }
}
