package com.ecs.latam;

import com.ecs.latam.kafka.domain.InvalidMCException;
import com.ecs.latam.kafka.domain.TransformMCtoXML;
import com.prowidesoftware.swift.utils.Lib;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

class TransformMCtoXMLTest {

    @Test
    public void transformationMCtoCAMTTest() throws InvalidMCException, IOException {
        TransformMCtoXML t = new TransformMCtoXML();
        String inHeader, inDetails;
        inHeader = Lib.readResource("Good_Header.txt");
        inDetails = Lib.readResource("Good_Details.txt");
        try {
            assertThat(t.transformationMCtoCAMT(inHeader,inDetails)).isNotEmpty();

        }catch (InvalidMCException e){
            assertThat(e.getMessage().isBlank()).isFalse();
        }

    }

}