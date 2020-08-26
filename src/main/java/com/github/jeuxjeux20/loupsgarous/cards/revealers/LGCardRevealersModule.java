package com.github.jeuxjeux20.loupsgarous.cards.revealers;

public final class LGCardRevealersModule extends CardRevealersModule {
    @Override
    protected void configureBindings() {

    }

    @Override
    protected void configureCardRevealers() {
        addCardRevealer(SelfCardRevealer.class);
        addCardRevealer(GameEndedCardRevealer.class);
        addCardRevealer(PlayerDeadCardRevealer.class);
        addCardRevealer(CoupleCardRevealer.class);
        addCardRevealer(VoyanteCardRevealer.class);
    }
}