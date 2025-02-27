package lotto.controller;

import java.util.List;
import java.util.Set;
import lotto.model.Game;
import lotto.model.Lotto;
import lotto.model.Player;
import lotto.service.LottoService;
import lotto.util.MajorErrorMessage;
import lotto.util.Validator;
import lotto.util.Validator.dataType;
import lotto.view.LottoView;

public class LottoController {
    final LottoView lottoView;
    final LottoService lottoService;


    public void startGame() {
        boolean buyContinue = true;
        boolean setGameContinue = true;

        while (buyContinue) {
            buyContinue = buyStep();
        }
        while (setGameContinue) {
            setGameContinue = setGameStep();
        }

        resultStep();
    }

    public boolean buyStep() {
        try {
            Player player = setLottoMoney();
            attemptLottoPurchase(player);
            lottoService.setPlayer(player);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    public boolean setGameStep() {
        try {
            Game gameStep = setJackpot();
            gameStep = setBonus(gameStep);

            lottoService.setGame(gameStep);
            return false;
        } catch (Exception e) {
            System.out.println(MajorErrorMessage.LOTTONUM_WRONG.getMessage() + e.getMessage());
            return true;
        }
    }

    public void resultStep() {
        int[] lottoResultTypes = lottoService.calculateLottoResultType();
        float lottoResultProfitPercent = lottoService.calculateLottoResultProfit(lottoResultTypes);
        lottoView.outputLottoResult(lottoResultTypes, lottoResultProfitPercent);
    }

    Player setLottoMoney() {
        try {
            String inputLine = lottoView.inputPurchaseMoney();
            return Player.setLottoMoney(Validator.isSingleInputType(inputLine, dataType.NUMBER));
        } catch (Exception e) {
            throw new IllegalArgumentException(MajorErrorMessage.MONEY_WRONG.getMessage() + e.getMessage());
        }
    }

    void attemptLottoPurchase(Player player) {
        try {
            player.buyLotto();
            lottoView.outputPurchasedLottoNum(player.getPurchasedLottoNum());
            player.getPurchasedLottoList().forEach(lotto -> lottoView.outputPurchasedLottoDetail(lotto.getNumbers()));
        } catch (Exception e) {
            throw new IllegalArgumentException(MajorErrorMessage.LOTTONUM_WRONG.getMessage() + e.getMessage());
        }
    }

    Game setJackpot() {
        String inputLine = lottoView.inputJackpotNumber();
        List<Integer> jackpotNumbers = Validator.isMultipleInputType(inputLine, dataType.NUMBER, ",");
        Validator.isListItemInRange(Lotto.getLottoRangeStart(), Lotto.getLottoRangeEnd(), jackpotNumbers);
        Set<Integer> jackpotNumberSet = Validator.isListItemDuplicated(jackpotNumbers);
        return Game.setJackpot(jackpotNumberSet);
    }

    Game setBonus(Game game) {
        String inputLine = lottoView.inputBonusNumber();
        int bonusNumber = Validator.isSingleInputType(inputLine, dataType.NUMBER);
        game.setBonus(bonusNumber);
        return game;
    }

    public LottoController(LottoView lottoView, LottoService lottoService) {
        this.lottoView = lottoView;
        this.lottoService = lottoService;
    }
}
