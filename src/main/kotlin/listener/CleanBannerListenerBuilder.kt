package listener

import org.bukkit.event.block.CauldronLevelChangeEvent
import util.matches

class CleanBannerListenerBuilder : ObjectiveListenerBuilder<CauldronLevelChangeEvent> {
    override fun createFactory(): ObjectiveListenerFactory<CauldronLevelChangeEvent> =
        ObjectiveListenerFactory { event, player ->
            event.reason == CauldronLevelChangeEvent.ChangeReason.BANNER_WASH && event.entity matches player
        }
}
