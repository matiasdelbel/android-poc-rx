package com.delbel.heritage.gateway.mock.domain

import com.delbel.heritage.domain.HeritageDetail

class HeritageDetailMock {

    companion object {

        val AACHEN_CATHEDRAL = HeritageDetail(
            id = "3",
            name = "Aachen Cathedral ",
            shortInfo = "Aachen Cathedral \n\nConstruction of this palatine chapel, with its octagonal basilica and cupola, began c. 790–800 under the Emperor Charlemagne. Originally inspired by the churches of the Eastern part of the Holy Roman Empire, it was splendidly enlarged in the Middle Ages. ",
            image = "http://whc.unesco.org//uploads/thumbs/site_0003_0001-750-0-20131014170237.jpg"
        )
    }
}