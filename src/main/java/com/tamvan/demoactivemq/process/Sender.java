package com.tamvan.demoactivemq.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class Sender {

    private final JmsTemplate jmsTemplate;

    public void kirimPesan(String message, Long scheduled) {
        sendData(constructMessageCreator(message, scheduled));
    }

    public void testKirimPesan(){
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Random rand = new Random();
        Date startDate = getStartDate();
        Date endDate = getEndDate();
        Calendar cal = Calendar.getInstance();
        for(int i=0;i<100;i++){
            String message = "Tegar Tampan - " + i;
            cal.clear();
            cal.setTime(new Date());
            cal.add(Calendar.MINUTE, 5 + rand.nextInt(30));
            if(startDate.after(cal.getTime())){
                startDate.setTime(cal.getTimeInMillis());
            }
            if(endDate.before(cal.getTime())){
                endDate.setTime(cal.getTimeInMillis());
            }
            log.info("Schedule message " + message + " at " + df.format(cal.getTime()));
            sendDataOther(constructMessageCreatorWithMinute(message, cal.getTimeInMillis()));
        }

        log.info("Message will be processed between " + df.format(startDate) + " and " + df.format(endDate));
    }

    private static Date getEndDate() {
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(new Date());
        endCal.add(Calendar.MINUTE, 7);
        return endCal.getTime();
    }

    private static Date getStartDate() {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(new Date());
        startCal.add(Calendar.MINUTE, 7);
        return startCal.getTime();
    }

    private void sendData(MessageCreator messageCreator) {
        jmsTemplate.send("tamvan", messageCreator);
    }

    private void sendDataOther(MessageCreator messageCreator) {
        jmsTemplate.send("tegar-tamvan", messageCreator);
    }

    private MessageCreator constructMessageCreator(String message, Long scheduled) {
        return session -> {
            Message messageCreated = session.createTextMessage(message);
            messageCreated.setLongProperty("_AMQ_SCHED_DELIVERY", System.currentTimeMillis() + scheduled);
            return messageCreated;
        };
    }

    private MessageCreator constructMessageCreatorWithMinute(String message, Long scheduled) {
        return session -> {
            Message messageCreated = session.createTextMessage(message);
            messageCreated.setLongProperty("_AMQ_SCHED_DELIVERY", scheduled);
            return messageCreated;
        };
    }
}
